package arkanoid.observer;

import arkanoid.gameobjects.Ball;
import arkanoid.gameobjects.Block;

/**
 * ScoreTrackingListener is in charge of Tracking the score of the game.
 * adding points whenever a block is being hitted.
 */
public class ScoreTrackingListener implements HitListener {

    //Members:
    private final Counter currentScore;

    //Constants:
    private static final int SCORE_PER_BLOCK = 10;

    /**
     * Constructor for the score listener.
     * @param scoreCounter - the initialized score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method will add score whenever a block has being hitted.
     * @param beingHit - a block that being hitted by a ball.
     * @param hitter - The hitter parameter is the Ball that hits a block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(SCORE_PER_BLOCK);
    }
}