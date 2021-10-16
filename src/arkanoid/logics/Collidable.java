//Guy Aronson 318654225
package arkanoid.logics;

import arkanoid.gameobjects.Ball;
import arkanoid.gameobjects.Point;
import arkanoid.gameobjects.Rectangle;

/**
 * An interface for the collidable shapes.
 * @author Guy Aronson
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return returns the rectangle which can be collided.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint - the collision point that is anticipated.
     * @param currentVelocity - the current velocity of the object that will hit at the collision point.
     * @param hitter - the ball that hits the block.
     * @return new velocity expected after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * A boolean to check if the ball got into the collidable object.
     * @param b - the ball object.
     * @return true if the ball is inside the collidable object, false otherwise.
     */
    boolean isBallInside(Ball b);
}
