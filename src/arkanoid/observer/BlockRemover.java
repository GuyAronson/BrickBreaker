package arkanoid.observer;

import arkanoid.gameobjects.Ball;
import arkanoid.gameobjects.Block;
import arkanoid.logics.GameLevel;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    //Members:
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Constructor for the block remover.
     * @param gameLevel - the game object.
     * @param remainedBlocks - counter for the remaining blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * Also this listener will be removed from the block that is being removed from the game.
     * And decreasing 1 from the counter.
     * @param beingHit - a block that being hitted by a ball.
     * @param hitter - The hitter parameter is the Ball that hits a block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.remainingBlocks.getValue() > 0) {
            this.gameLevel.removeCollidable(beingHit);
            this.gameLevel.removeSprite(beingHit);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}
