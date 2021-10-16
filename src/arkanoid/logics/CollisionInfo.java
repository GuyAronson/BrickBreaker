//Guy Aronson 318654225
package arkanoid.logics;

import arkanoid.gameobjects.Point;

/**
 * A class for the collisions information.
 * @author Guy Aronson
 */
public class CollisionInfo {
    //Members:
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * A constructor for the Collision info, creates the relevant instances and holds the information.
     * @param collision - the collision's point.
     * @param object - the collision object -(block/paddle)
     */
    public CollisionInfo(Point collision, Collidable object) {
        this.collisionPoint = collision;
        this.collisionObject = object;
    }

    /**
     * Accessor.
     * @return The point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Accessor.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}