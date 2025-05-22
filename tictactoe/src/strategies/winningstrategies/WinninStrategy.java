package strategies.winningstrategies;

import models.Board;
import models.Move;

public interface WinninStrategy {
    boolean checkWinner(Board board, Move move);
}
