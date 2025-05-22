package strategies.botplaystrategies;

import models.Board;
import models.Cell;

public interface BotPlayStrategy {
    Cell makeMove(Board board);
}
