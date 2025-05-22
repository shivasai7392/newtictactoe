package strategies.botplaystrategies;

import models.Board;
import models.BotDifficultyLevel;

public class BotPlayStrategyFactory {
    public static BotPlayStrategy getBotPlayStrategy(BotDifficultyLevel difficultyLevel) {
        return switch (difficultyLevel){
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
        };
    }
}
