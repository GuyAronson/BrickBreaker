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
 * A class hold information about the first level.
 * @author Guy Aronson.
 */
public class Level1 implements LevelInformation {

    //Constants:
    private static final String NAME = "DirectHit";
    private static final int AMOUNT_OF_BALLS = 1;
    private static final int AMOUNT_OF_BLOCKS = 1;
    private static final int PADDLE_MOVEMENT = 10;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BLOCK_SIDE = 50;
    private static final int BALL_RADIUS = 5;
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    @Override
    public int numberOfBalls() {
        return AMOUNT_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        //Creating a single velocity to the single ball in this level.
        Velocity v = new Velocity(0, 7);
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(v);

        return velocities;
    }

    @Override
    public List<Ball> balls() {
        double ballX = (double) (GameLevel.WIDTH / 2);
        double ballY = 300;

        Ball b = new Ball(ballX, ballY, BALL_RADIUS, Color.WHITE);
        List<Ball> balls = new ArrayList<>();
        balls.add(b);

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
        return new Point((double) (GameLevel.WIDTH - PADDLE_WIDTH) / 2,
                GameLevel.HEIGHT - GameLevel.BOUNDARY_SIZE - PADDLE_HEIGHT);
    }

    @Override
    public List<Block> blocks() {
        double blockX = paddleLocation().getX() + (double) (BLOCK_SIDE / 2);
        double blockY = 100;

        Block b = new Block(new Rectangle(new Point(blockX, blockY), BLOCK_SIDE, BLOCK_SIDE), Color.RED);
        List<Block> blocks = new ArrayList<>();
        blocks.add(b);
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
