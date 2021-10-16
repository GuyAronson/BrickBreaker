//Guy Aronson 318654225
package arkanoid.logics;

import arkanoid.gameobjects.Line;
import arkanoid.gameobjects.Point;

import java.util.ArrayList;
import java.util.List;
/**
 * A class for the game environment which will be a collection of a collidable objects.
 * @author Guy Aronson
 */
    public class GameEnvironment {

    //Members:
    private final List<Collidable> blocks;

    /**
     * A defualt constructor for the game environment.
     * Creates a list for the collidable objects.
     */
    public GameEnvironment() {
        this.blocks = new ArrayList<Collidable>();
    }

    /**
     * A constructor for the game environment -
     * create a list for the collidables object by the list given as parameter.
     * @param collidables - A list of collidables to insert to the blocks field.
     */
    public GameEnvironment(List<Collidable> collidables) {
        if (collidables != null) {
            this.blocks = new ArrayList<Collidable>(collidables);
        } else {
            this.blocks = new ArrayList<Collidable>();
        }
    }

    /**
     * Add the given collidable to the environment.
     * @param c - A collidable object
     */
    public void addCollidable(Collidable c) {
        this.blocks.add(c);
    }

    /**
     * Add the given list of collidables to the environment.
     * @param collidables - A List of collidables
     */
    public void addCollidables(List<Collidable> collidables) {
        this.blocks.addAll(collidables);
    }

    /**
     * Accessor.
     * @return - returns the environment, the collidable objects list.
     */
    public List<Collidable> getEnvironment() {
        return this.blocks;
    }

    /**
     * This method gets all of the collision points the line might have with the collidable objects
     * eventually returns the closest one.
     * @param trajectory - the path that the object might move by
     * @return the closest collision point, If there isn't any collision points - returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> closestPoints = new ArrayList<>();
        for (int i = 0; i < this.blocks.size(); i++) {

            /*Adding to the closestPoints list, the closest point, for any collision between the trajectory
            and any rectangle in the blocks list.*/
            Point intersection = trajectory.closestIntersectionToStartOfLine(
                    this.blocks.get(i).getCollisionRectangle());
            if (intersection != null) {

                //Adds to the list the relevant collision info - collision object & point.
                closestPoints.add(new CollisionInfo(intersection, this.blocks.get(i)));
            }
        }

        /*At this point we have in the closestPoints list - collision infos, each one contains the
        collision point & object, now its time to check which point in this list is the closest */
        int index = -1;
        double distance, minDistance = Double.POSITIVE_INFINITY;
        for (int i = 0; i < closestPoints.size(); i++) {

            /*Gets the distance between the start point and each point in the list - and checks if its the
            lowest. */
            distance = trajectory.getStart().distance(closestPoints.get(i).collisionPoint());
            if (distance < minDistance) {
                minDistance = distance;

                //Save the index of the collision info in the list.
                index = i;
            }
        }
        if (index < 0) {
            return null;
        } else {
            return closestPoints.get(index);
        }
    }
}