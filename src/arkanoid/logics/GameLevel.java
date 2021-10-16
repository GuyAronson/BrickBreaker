package arkanoid.logics;

import arkanoid.animation.Animation;
import arkanoid.animation.PauseScreen;
import arkanoid.animation.AnimationRunner;
import arkanoid.animation.CountdownAnimation;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.gameobjects.Block;
import arkanoid.gameobjects.Ball;
import arkanoid.gameobjects.Paddle;
import arkanoid.gameobjects.Rectangle;
import arkanoid.gameobjects.Point;
import arkanoid.level.LevelInformation;
import arkanoid.observer.BallRemover;
import arkanoid.observer.BlockRemover;
import arkanoid.observer.Counter;
import arkanoid.observer.LevelName;
import arkanoid.observer.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * @author Guy Aronson, 318654225
 * This class will run the game and the animation.
 */
public class GameLevel implements Animation {

    //Constants:
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BOUNDARY_SIZE = 20;
    private static final int LEVEL_COMPLETED = 100;

    //Members:
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter remainedBlocks;
    private final Counter remainedBalls;
    private final Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor key;
    private final LevelInformation levelInformation;

    /**
     * Constructor for the game class, holds the relevant details by the level information instance.
     * @param levelInfo - which level will be initialize.
     * @param totalscore - the total score of the game, given to each level.
     * @param run - AnimationRunner that runs the whole game.
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner run,  Counter totalscore) {
        this.levelInformation = levelInfo;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainedBlocks = new Counter(this.levelInformation.totalAmountOfBlocks());
        this.remainedBalls = new Counter(this.levelInformation.numberOfBalls());
        this.score = totalscore;
        this.runner = run;
        this.key = this.runner.getGUI().getKeyboardSensor();
    }

    /**
     * A method to add a collidable object to the environment.
     * @param c - the collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * A method to add a sprite object to the sprite collection.
     * @param s - the sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method removes a collidable object from the game environment.
     * @param c - the collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {

        //Getting the Collidables list, and removing the given collidable.
        this.environment.getEnvironment().remove(c);
    }

    /**
     * This method removes a sprite object from the sprites collection.
     * @param s - the sprite object to be removed.
     */
    public void removeSprite(Sprite s) {

        //Getting the sprites list, and removing the given sprite from the collection.
        this.sprites.getSprites().remove(s);
    }

    /**
     * Accessor.
     * @return the game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Accessor.
     * @return the remained blocks in this level.
     */
    public Counter getRemainedBlocks() {
        return this.remainedBlocks;
    }

    /**
     * Accessor.
     * @return the remained balls in the level.
     */
    public Counter getRemainedBalls() {
        return this.remainedBalls;
    }

    /**
     *  Initialize a new game: create the Blocks and Ball (and Paddle)
     *  and add them to the game.
     */
    public void initialize() {
        Color boundariesColor = Color.GRAY;

        //Initialize the listeners:
        BlockRemover blockRemover = new BlockRemover(this, this.remainedBlocks);
        BallRemover ballRemover = new BallRemover(this, remainedBalls);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);

        //Initialize the background:
        this.addSprite(this.levelInformation.getBackground());


        //Intialize the death region:
        Block deathRegion = new Block(new Rectangle(new Point(0, HEIGHT - BOUNDARY_SIZE),
                WIDTH, BOUNDARY_SIZE), this.levelInformation.getBackgroundColor());
        this.addCollidable(deathRegion);
        this.addSprite(deathRegion);
        deathRegion.addHitListener(ballRemover);

        //Intialize the boundaries:
        Block blockTop = new Block(new Rectangle(new Point(0, BOUNDARY_SIZE), WIDTH,  BOUNDARY_SIZE),
                boundariesColor);
        Block blockLeft = new Block(new Rectangle(new Point(0, BOUNDARY_SIZE * 2), BOUNDARY_SIZE, HEIGHT),
                boundariesColor);
        Block blockRight = new Block(new Rectangle(new Point(WIDTH - BOUNDARY_SIZE, BOUNDARY_SIZE * 2),
                BOUNDARY_SIZE, HEIGHT), boundariesColor);
        this.addCollidable(blockTop);
        this.addCollidable(blockLeft);
        this.addCollidable(blockRight);
        this.addSprite(blockTop);
        this.addSprite(blockLeft);
        this.addSprite(blockRight);

        //Initialize the score indicator:
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        this.addSprite(scoreIndicator);

        //Initialize the Game level object:
        LevelName name = new LevelName(this.levelInformation.levelName());
        this.addSprite(name);

        //Initialize the paddle.
        Paddle paddle = new Paddle(new Rectangle(this.levelInformation.paddleLocation(),
                this.levelInformation.paddleWidth(), this.levelInformation.paddleHeight()), key);
        paddle.addToGame(this);

        //Running on the blocks of each level, adding the listeners to them. and adding them to the sprites.
        List<Block> blocks = this.levelInformation.blocks();
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).addHitListener(blockRemover);
            blocks.get(i).addHitListener(scoreTracker);
            this.addCollidable(blocks.get(i));
            this.addSprite(blocks.get(i));
        }

        //Running on the balls and adding them to the game:
        List<Ball> balls = this.levelInformation.balls();
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < this.levelInformation.balls().size(); i++) {
            balls.get(i).setVelocity(velocities.get(i));
            this.addSprite(balls.get(i));
            balls.get(i).setEnvironment(this.environment);
        }

        /*//Intialize the balls
        for (int i = 0; i < 50; i++) {
            Ball ball = new Ball(new Point(500 + (i * 2), 400), 5);
            ball.setVelocity(-6 + (i * 0.25), -4 + (i * 0.1));
            this.addSprite(ball);
            ball.setEnvironment(this.environment);
        }
        Ball ball1 = new Ball(new Point(500, 50), 5);
        ball1.setVelocity(3, 3);
        Ball ball2 = new Ball(new Point(600, 400), 5);
        ball2.setVelocity(3, -3);
        Ball ball3 = new Ball(new Point(200, 400), 5);
        ball3.setVelocity(4, 3);
        this.sprites.addSprite(ball1);
        this.sprites.addSprite(ball2);
        this.sprites.addSprite(ball3);
        ball1.setEnvironment(this.environment);
        ball2.setEnvironment(this.environment);
        ball3.setEnvironment(this.environment);*/

    }

    /**
     *Run the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;

        /* use our runner to run the current animation -
        - which is one turn of the game.*/
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int blocksRemainedAfterCompleteLevel =
                this.levelInformation.totalAmountOfBlocks() - this.levelInformation.numberOfBlocksToRemove();

        //Exit the game if no more blocks are available:
        if (this.remainedBlocks.getValue() == blocksRemainedAfterCompleteLevel) {
            this.score.increase(LEVEL_COMPLETED);
            this.running = false;
        } else if (this.remainedBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.key.isPressed("p") || this.key.isPressed("פ")) {
            this.runner.run(new KeyPressStoppableAnimation(this.key, "space", new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }
}