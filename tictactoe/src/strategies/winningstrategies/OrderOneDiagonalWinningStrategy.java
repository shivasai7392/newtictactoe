package strategies.winningstrategies;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneDiagonalWinningStrategy implements WinninStrategy{
    private List<HashMap<Character, Integer>> rowCounter;
    private int dimension;

    public OrderOneDiagonalWinningStrategy(int dimension, List<Player> players) {
        this.dimension = dimension;
        this.rowCounter = new ArrayList<>();
        for (int i = 0; i<dimension; ++i){
            HashMap<Character, Integer> map = new HashMap<>();
            for (Player player : players){
                map.put(player.getSymbol().getSymbol(), 0);
            }
            this.rowCounter.add(map);
        }
    }

    @Override
    public void undoMove(Move move) {
        int row = move.getCell().getRow();
        HashMap<Character, Integer> map = this.rowCounter.get(row);
        map.compute(move.getPlayer().getSymbol().getSymbol(), (k, char_count) -> char_count - 1);
    }

    @Override
    public boolean checkWinner(Move move) {
        int row = move.getCell().getRow();
        HashMap<Character, Integer> map = this.rowCounter.get(row);
        int char_count = map.get(move.getPlayer().getSymbol().getSymbol());
        char_count += 1;
        map.put(move.getPlayer().getSymbol().getSymbol(), char_count);
        return char_count == this.dimension;
    }
}
