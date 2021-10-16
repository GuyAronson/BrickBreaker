package arkanoid.observer;

import arkanoid.gameobjects.Ball;
import arkanoid.gameobjects.Block;
import arkanoid.logics.GameLevel;

/**
 * Ball Remover is in charge of removing balls from the game.
 * happens only if the ball got into the death region.
 */
public class BallRemover implements HitListener {

    //Members:
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Constructor for the ball remover.
     * @param gameLevel - the game object.
     * @param remainedBalls - counter for the remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter remainedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainedBalls;
    }

    /**
     * Balls that got into the death region will be removed from the game.
     * Also this listener will be removed from the ball that has been removed from the game.
     * And decreasing 1 from the counter.
     * @param beingHit - a block that being hitted by a ball.
     * @param hitter - The hitter parameter is the Ball that hits a block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.remainingBalls.getValue() > 0) {
            hitter.removeFromGame(this.gameLevel);
            this.remainingBalls.decrease(1);
        }
    }
}
