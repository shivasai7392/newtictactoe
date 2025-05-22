package strategies.winningstrategies;

import models.Board;
import models.Move;

public interface WinninStrategy {
    boolean checkWinner(Move move);
    void undoMove(Move move);
}
