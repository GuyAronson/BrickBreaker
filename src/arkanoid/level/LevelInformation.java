package arkanoid.level;

import arkanoid.gameobjects.Ball;
import arkanoid.gameobjects.Block;
import arkanoid.gameobjects.Point;
import arkanoid.logics.Sprite;
import arkanoid.logics.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * A Interface to be implemented by the different levels.
 * @author Guy Aronson.
 */
public interface LevelInformation {

    /**
     * Method returns the number of balls in the current level.
     * @return - the number of balls in the specific level.
     */
    int numberOfBalls();

    /**
     * @return a list contains the balls of this level.
     */
    List<Ball> balls();

    /**
     * The initial velocity of each ball.
     * @return a list of the balls velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The intialized paddle's speed.
     * @return the paddle's speed in the current level.
     */
    int paddleSpeed();

    /**
     * The initialized paddle's width.
     * @return the paddle's width in the current level.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return the level's name.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return a list contains the blocks of this level,each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return number of blocks that needs to be removed.
     */
    int numberOfBlocksToRemove();

    /**
     * The number of blocks this level contains at start.
     * @return number of blocks
     */
    int totalAmountOfBlocks();

    /**
     * Method returns the paddle location.
     * @return the upper left point of the paddle.
     */
    Point paddleLocation();

    /**
     * @return the paddle's height of the current level.
     */
    int paddleHeight();

    /**
     * @return the background's Color.
     */
    Color getBackgroundColor();

    /**
     * This method will draw the level on the gui.
     * @param d - the surface to draw on.
    void draw(DrawSurface d);*/
}