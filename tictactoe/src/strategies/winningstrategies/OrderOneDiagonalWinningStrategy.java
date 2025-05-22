package strategies.winningstrategies;

import models.Board;
import models.Move;

public class OrderOneDiagonalWinningStrategy implements WinninStrategy{
    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
