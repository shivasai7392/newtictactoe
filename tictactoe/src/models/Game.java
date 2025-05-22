package models;

import strategies.winningstrategies.WinninStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private Player winner;
    private int currentPlayerIndex;
    private List<WinninStrategy> winningStrategies;
    private GameStatus status;

    public Game(List<Player> players, int dimension, List<WinninStrategy> winningStrategies) {
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
}
