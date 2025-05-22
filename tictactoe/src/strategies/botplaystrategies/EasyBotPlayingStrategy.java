package strategies.botplaystrategies;

import models.Board;
import models.Cell;
import models.CellStatus;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayStrategy{
    @Override
    public Cell makeMove(Board board) {
        for (List<Cell> row : board.getGrid()){
            for (Cell cell : row){
                if (cell.getStatus().equals(CellStatus.EMPTY)){
                    return cell;
                }
            }
        }
        return null;
    }
}
