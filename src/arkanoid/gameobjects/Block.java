//Guy Aronson - 318654225
package arkanoid.gameobjects;

import arkanoid.logics.Collidable;
import arkanoid.logics.GameLevel;
import arkanoid.logics.Sprite;
import arkanoid.logics.Velocity;
import arkanoid.observer.HitListener;
import arkanoid.observer.HitNotifier;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for the blocks in the game, implements the collidable interface.
 * @author Guy Aronson.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //Members:
    private final Rectangle block;
    private final java.awt.Color color;
    private final List<HitListener> hitListeners;

    //Constants:
    private static final double WIDTH = 100;
    private static final double HEIGHT = 50;

    /**
     * Constructor for the block gets a rectangle as paramter.
     * @param rect - The block's rectangle.
     * @param color - rectangle's color.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.block = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor - gets only upperleft point as parameter, has default width and height.
     * @param upperLeft - upper left point of the block.
     * @param color  - block's color.
     * Calls the other constructor.
     */
    public Block(Point upperLeft, java.awt.Color color) {
        this(new Rectangle(upperLeft, WIDTH, HEIGHT), color);

    }

    /**
     * Accessor.
     * @return the block's rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Method to return the expected velocity once the object collided into the block.
     * @param collisionPoint - the collision point that is anticipated.
     * @param currentVelocity - the current velocity of the object that will hit at the collision point.
     * @param hitter - the ball the hits the block.
     * @return the new expected velocity after the object hit the block.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double collisionX = collisionPoint.getX(), collisionY = collisionPoint.getY();
        final double epsilon = Math.pow(10, -10);

        this.notifyHit(hitter);

        //Checks if the collision point is on the top/bottom side of the block.
        if (Math.abs(this.block.getUpperLeft().getY() - collisionY) < epsilon
                || Math.abs((this.block.getUpperLeft().getY() + this.block.getHeight()) - collisionY) < epsilon) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }

        //Checks if the collision point is on the left/right side of the block.
        if (Math.abs(this.block.getUpperLeft().getX() - collisionX) < epsilon
                || Math.abs((this.block.getUpperLeft().getX() + this.block.getWidth()) - collisionX) < epsilon) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * The function draws the block on the given surface by its color.
     * @param surface - the surface which the block will be drawn on.
     */
    public void drawOn(biuoop.DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * Notify that time has passed.
     */
    public void timePassed() {
    }

    /**
     * A boolean method that checks whether the ball got inside the block.
     * @param b - the ball object.
     * @return true if the ball is inside the block, false otherwise.
     */
    public boolean isBallInside(Ball b) {
        double ballX = b.getX(), ballY = b.getY();
        double rectX = this.block.getUpperLeft().getX(), rectY = this.block.getUpperLeft().getY();
        double height = this.block.getHeight(), width = this.block.getWidth();

        if (ballX > rectX &&  ballX < rectX + width) {
            if (ballY > rectY && ballY < rectY + height) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to remove a block from the game.
     * @param gameLevel - the game parameter to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * This method notifying all of the hit-listeners that his has been occurred.
     * @param hitter - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
