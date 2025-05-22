package models;

import strategies.botplaystrategies.BotPlayStrategy;
import strategies.botplaystrategies.BotPlayStrategyFactory;

public class Bot extends Player {
    private BotDifficultyLevel difficultyLevel;
    private BotPlayStrategy botPlayStrategy;

    public Bot(String name, Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
        this.botPlayStrategy = BotPlayStrategyFactory.getBotPlayStrategy(difficultyLevel);
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public Cell makeMove(Board board) {
        return botPlayStrategy.makeMove(board);
    }
}
