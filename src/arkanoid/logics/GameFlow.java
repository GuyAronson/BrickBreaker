package arkanoid.logics;

import arkanoid.animation.AnimationRunner;
import arkanoid.animation.GameOver;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.animation.WinningScreen;
import arkanoid.level.LevelInformation;
import arkanoid.observer.Counter;

import java.util.List;

/**
 * This GameFlow class will be in charge of the flowing of the game,
 * which means jumping between the different levels.
 */
public class GameFlow {

    //Members:
    private final Counter score;
    private final AnimationRunner runner;

    /**
     * Constructor for the GameFlow class.
     * this way we can create an instance that will run the game levels.
     */
    public GameFlow() {
        this.score = new Counter();
        this.runner = new AnimationRunner();
    }

    /**
     * Method to to run the different levels given as parameter.
     * @param levels - the levels to run in this game.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isLost = false;

        //This loop will run throughout the levels and will initialize and run each one of them.
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.runner, this.score);

            level.initialize();
            int levelAmountOfBlocks = levelInfo.totalAmountOfBlocks();
            int numberOfBlocksToRemove = levelInfo.numberOfBlocksToRemove();

            /* This loop keeps the level running.
            * Once there are no more balls - which means they all fell, the player lost, and the level
            * stops running.
            * If the player hit numberOfBlocksToRemove blocks, then he won this level, and it stops running.*/
            while (level.getRemainedBalls().getValue() != 0
                    && levelAmountOfBlocks - numberOfBlocksToRemove != level.getRemainedBlocks().getValue()) {
                level.run();
            }

            //If the level has no more balls.
            if (level.getRemainedBalls().getValue() == 0) {
                isLost = true;
                this.runner.run(new KeyPressStoppableAnimation(this.runner.getGUI().getKeyboardSensor(),
                        "space", new GameOver(this.score)));
                break;
            }
        }
        if (!isLost) {
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getGUI().getKeyboardSensor(),
                    "space", new WinningScreen(this.score)));
        }
        this.runner.closeGUI();
    }
}
