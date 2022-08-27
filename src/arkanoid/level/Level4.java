package arkanoid.level;

import arkanoid.gameobjects.Ball;
import arkanoid.gameobjects.Block;
import arkanoid.gameobjects.Point;
import arkanoid.gameobjects.Rectangle;
import arkanoid.logics.GameLevel;
import arkanoid.logics.Sprite;
import arkanoid.logics.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class hold information about the four level.
 * @author Guy Aronson.
 */
public class Level4 implements LevelInformation {

    //Constants:
    private static final String NAME = "Final Four";
    private static final int AMOUNT_OF_BALLS = 3;
    private static final int AMOUNT_OF_BLOCKS = 105;
    private static final int PADDLE_MOVEMENT = 10;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BALL_RADIUS = 5;
    private static final Color BACKGROUND_COLOR = new Color(50, 100, 200);

    @Override
    public int numberOfBalls() {
        return AMOUNT_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v1 = new Velocity(3, -3);
        velocities.add(v1);
        Velocity v2 = new Velocity(3, -1);
        velocities.add(v2);
        Velocity v3 = new Velocity(-3, 3);
        velocities.add(v3);

        return velocities;
    }

    @Override
    public List<Ball> balls() {
        double startBallX = 300;
        double startBallY = 350;
        double gap = 150;
        List<Ball> balls = new ArrayList<>();

        Ball b1 = new Ball(startBallX, startBallY, BALL_RADIUS, Color.WHITE);
        balls.add(b1);
        Ball b2 = new Ball(startBallX + (gap / 2), startBallY + (gap / 5), BALL_RADIUS, Color.WHITE);
        balls.add(b2);
        Ball b3 = new Ball(startBallX + gap, startBallY, BALL_RADIUS, Color.WHITE);
        balls.add(b3);

        return balls;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_MOVEMENT;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public int paddleHeight() {
        return PADDLE_HEIGHT;
    }

    @Override
    public String levelName() {
        return NAME;
    }

    @Override
    public Sprite getBackground() {

        //Returning the background of the level.
        return new Block(new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT),
                BACKGROUND_COLOR);
    }

    @Override
    public Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    @Override
    public Point paddleLocation() {
        return new Point((double) (GameLevel.WIDTH / 2 - PADDLE_WIDTH / 2),
                GameLevel.HEIGHT - GameLevel.BOUNDARY_SIZE - PADDLE_HEIGHT);
    }

    @Override
    public List<Block> blocks() {
        int rows = 7, rowAmountOfBlocks = AMOUNT_OF_BLOCKS / rows;
        double blocksStartX = GameLevel.BOUNDARY_SIZE * 1.25;
        double blocksStartY = 100;
        Color[] colors = {Color.DARK_GRAY, Color.RED, Color.YELLOW, Color.CYAN, Color.PINK, Color.GREEN,
                Color.WHITE};
        List<Block> blocks = new ArrayList<>();

        /*Creating the colorful blocks.
         * Each row, has different color and different amount of blocks.*/
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rowAmountOfBlocks; j++) {
                Block block = new Block(new Rectangle(new Point(blocksStartX + (j * BLOCK_WIDTH),
                        blocksStartY + (BLOCK_HEIGHT * i)), BLOCK_WIDTH, BLOCK_HEIGHT), colors[i]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return AMOUNT_OF_BLOCKS;
    }

    @Override
    public int totalAmountOfBlocks() {
        return AMOUNT_OF_BLOCKS;
    }
}
