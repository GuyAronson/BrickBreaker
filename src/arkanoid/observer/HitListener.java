package arkanoid.observer;

import arkanoid.gameobjects.Ball;
import arkanoid.gameobjects.Block;

/**
 * Objects that want to be notified of hit events,
 * should implement the HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - a block that being hitted by a ball.
     * @param hitter - The hitter parameter is the Ball that hits a block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
