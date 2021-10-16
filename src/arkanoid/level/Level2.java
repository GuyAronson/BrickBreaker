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
 * A class hold information about the second level.
 * @author Guy Aronson.
 */
public class Level2 implements LevelInformation {

    //Constants:
    private static final String NAME = "Wide Easy";
    private static final int AMOUNT_OF_BALLS = 10;
    private static final int AMOUNT_OF_BLOCKS = 15;
    private static final int PADDLE_MOVEMENT = 10;
    private static final int PADDLE_WIDTH = 600;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BALL_RADIUS = 5;
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240);


    @Override
    public int numberOfBalls() {
        return AMOUNT_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 1; i <= AMOUNT_OF_BALLS / 2; i++) {
            Velocity v = new Velocity(-2 + (i * 0.25), -4 + (i * 0.25));
            velocities.add(v);

        }
        for (int i = AMOUNT_OF_BALLS / 2; i <= AMOUNT_OF_BALLS; i++) {
            Velocity v = new Velocity(2 + (i * 0.25), -4 + (i * 0.25));
            velocities.add(v);
        }

        return velocities;
    }

    @Override
    public List<Ball> balls() {
        double startBallX = 250;
        double startBallY = 480;
        double gap = 20;
        double goRight = 300;
        List<Ball> balls = new ArrayList<>();

        /*Creating the balls in different places with gaps between one another -
        and adding them to the balls list */
        for (int i = 0; i < AMOUNT_OF_BALLS; i++) {
            Ball b;
            if (i < AMOUNT_OF_BALLS / 2) {
                b = new Ball(startBallX + (i * gap),
                        startBallY - (i * gap), BALL_RADIUS, Color.BLACK);
            } else {

                /*Starting to loop again from 0 to 5, and building the balls the other way around.*/
                int newI = i - AMOUNT_OF_BALLS / 2;

                b = new Ball(startBallX + goRight - (newI * gap),
                        startBallY - (newI * gap), BALL_RADIUS, Color.BLACK);
            }
            balls.add(b);
        }

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
        double spaceLeft = GameLevel.WIDTH - PADDLE_WIDTH - (GameLevel.BOUNDARY_SIZE * 2);
        return new Point((double) GameLevel.BOUNDARY_SIZE + (spaceLeft / 2),
                GameLevel.HEIGHT - GameLevel.BOUNDARY_SIZE - PADDLE_HEIGHT);
    }

    @Override
    public List<Block> blocks() {
        double blockX = GameLevel.BOUNDARY_SIZE;
        double blockY = 300;
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.RED, Color.BLUE, Color.CYAN, Color.YELLOW, Color.GREEN};
        for (int i = 0; i < AMOUNT_OF_BLOCKS; i++) {
            Block b = new Block(new Rectangle(new Point(blockX + (i * BLOCK_WIDTH), blockY),
                    BLOCK_WIDTH, BLOCK_HEIGHT), colors[i % 5]);
            blocks.add(b);
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
