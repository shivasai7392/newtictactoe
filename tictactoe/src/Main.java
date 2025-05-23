import controllers.GameController;
import models.*;
import strategies.winningstrategies.OrderOneColWinningStrategy;
import strategies.winningstrategies.OrderOneDiagonalWinningStrategy;
import strategies.winningstrategies.OrderOneRowWinningStrategy;
import strategies.winningstrategies.WinninStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Create a game
        GameController gameController = new GameController();
        List<WinninStrategy> winningStrategies = new ArrayList<>();
        int dimension = 3;
        List<Player> players = List.of(
                new Player("sai", new Symbol('X'), PlayerType.HUMAN),
                new Player("shiva", new Symbol('O'), PlayerType.HUMAN)
        );
        Game game;
        try {
            game = gameController.createGame(
                    dimension,
                    players,
                    List.of(
                            new OrderOneColWinningStrategy(dimension, players),
                            new OrderOneDiagonalWinningStrategy(dimension, players),
                            new OrderOneRowWinningStrategy(dimension, players)
                    )
            );
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("********************** Game is Starting **********************");
        while (gameController.getGameStatus(game).equals(GameStatus.INPROGRESS)){
            //print board
            //While game is in progress
            //print if undo
            //if yes call undo
            //else makemove
            gameController.displayBoard(game);

            System.out.println("Do you want to undo? (y/n)");
            String input = scanner.nextLine();
            if (input.equals("y")){
                gameController.undo(game);
            }
            else{
                gameController.makeMove(game);
            }

        }

        //get game status
        //check if winner -> print winner
        //else -> print draw

        GameStatus gameStatus = gameController.getGameStatus(game);
        if (gameStatus == GameStatus.ENDED){
            gameController.printWinner(game);
        }
        else{
            System.out.println(gameStatus);
        }
    }
}