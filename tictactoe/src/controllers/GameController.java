package controllers;

import models.Game;
import models.GameStatus;
import models.Player;
import strategies.winningstrategies.WinninStrategy;

import java.util.List;

public class GameController {


    public Game createGame(int dimension, List<Player> players, List<WinninStrategy> winningStrategies){
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies).build();
    }

    public void displayBoard(Game game){
        game.printBoard();
    }

    public void undo(Game game){

    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public GameStatus getGameStatus(Game game){
        return game.getStatus();
    }

    public void printWinner(Game game){
        game.printWinner();
    }

}
