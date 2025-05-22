package models;

import strategies.winningstrategies.WinninStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private Player winner;
    private int currentPlayerIndex;
    private List<WinninStrategy> winningStrategies;
    private GameStatus status;

    public Game(int dimension, List<Player> players, List<WinninStrategy> winningStrategies) {
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.status = GameStatus.INPROGRESS;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public List<WinninStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinninStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void printBoard(){
       this.board.printBoard();
    }

    public void printWinner(){
        System.out.println("Congratulations " + this.winner.getName() + " you are the winner.");
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void makeMove(){
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s turn");
        Cell proposedCell = currentPlayer.makeMove(board);
        System.out.println("Move made at "+ proposedCell.getRow()+","+proposedCell.getColumn());
        if (!validateCell(proposedCell)){
            System.out.println("Invalid cell! Please try again");
            return;
        }
        Cell cellInBoard = board.getGrid().get(proposedCell.getRow()).get(proposedCell.getColumn());
        cellInBoard.setStatus(CellStatus.OCCUPIED);
        cellInBoard.setPlayer(currentPlayer);

        Move move = new Move(currentPlayer, cellInBoard);
        moves.add(move);

        if (checkWinner(move, currentPlayer)) return;

        if (moves.size() == board.getDimension()*board.getDimension()){
            status = GameStatus.DRAW;
            return;
        }

        currentPlayerIndex++;
        currentPlayerIndex %= players.size();
    }

    private boolean checkWinner(Move move, Player currentPlayer) {
        for (WinninStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(move)){
                this.status = GameStatus.ENDED;
                winner = currentPlayer;
                return true;
            }
        }
        return false;
    }

    private boolean validateCell(Cell cell){
        int row = cell.getRow();
        int column = cell.getColumn();
        if (row < 0 || column < 0 || row >= this.board.getDimension() || column >= this.board.getDimension()) {
            return false;
        }
        return board.getGrid().get(row).get(column).getStatus().equals(CellStatus.EMPTY);
    }

    public void undo(){
        if (this.moves.size() == 0){
            System.out.println("Undo is not Available.");
            return;
        }
        Move lastMove = this.moves.get(this.moves.size()-1);
        Cell cell = lastMove.getCell();
        Cell cellInBoard = this.board.getGrid().get(cell.getRow()).get(cell.getColumn());
        cellInBoard.setPlayer(null);
        cellInBoard.setStatus(CellStatus.EMPTY);
        this.moves.remove(this.moves.size()-1);

        for (WinninStrategy winningStrategy : this.winningStrategies){
            winningStrategy.undoMove(lastMove);
        }

        this.currentPlayerIndex -= 1;
        this.currentPlayerIndex += this.players.size();
        this.currentPlayerIndex %= this.players.size();
    }

    public static class Builder {
        private List<Player> players;
        private List<WinninStrategy> winningStrategies;
        private int dimension;
        private Builder() {

        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinninStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private boolean valid(){
            if (this.players == null || this.players.isEmpty() || this.winningStrategies == null || this.winningStrategies.isEmpty()){
                return false;
            }
            if (this.players.size() != this.dimension - 1){
                return false;
            }

            Set<Character> existingSymbols = new HashSet<>();
            int botCount = 0;

            for (Player player : this.players){
                if (player.getPlayerType() == PlayerType.BOT){
                    botCount++;
                }
                if (existingSymbols.contains(player.getSymbol().getSymbol())){
                    return false;
                }
                else {
                    existingSymbols.add(player.getSymbol().getSymbol());
                }
            }
            if (botCount >= 2){
                return false;
            }
            return true;
        }

        public Game build(){
            if (this.valid()){
                return new Game(dimension, players, winningStrategies);
            }
            throw new IllegalArgumentException("Invalid game");
        }
    }
}
