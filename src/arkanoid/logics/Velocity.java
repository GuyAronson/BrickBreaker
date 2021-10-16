// Guy Aronson - 318654225
package arkanoid.logics;

import arkanoid.gameobjects.Point;

/**
 * A class to define velocity for the animation.
 * @author Guy Aronson.
 */
public class Velocity {

    //Members:
    private double dx;
    private double dy;

    /**
     * Constructor for the velocity class.
     * @param dx - the dx movement
     * @param dy - the dy movement
     * Creates a velocity instance
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * another Constructor for the velocity instances.
     * @param v - the velocity instance.
     * Call the default constructor with the appropriate data
     */
    public Velocity(Velocity v) {
        this(v.getDx(), v.getDy());
    }

    /**
     * Function creates and returns a velocity instance by angle and speed.
     * calculates the dx & dy by the angle and speed.
     * @param angle - the angle's movement
     * @param speed - the speed's movement
     * @return new velocity instance.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Accessor.
     * @return the velocity's dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Accessor.
     * @return the velocity's dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * setDx purpose is to change the x movement.
     * @param newDx - new dx set.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * setDy purpose is to change the y movement.
     * @param newDy - new dy set.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * This function applies the velocity toa point.
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     * @param p - A point to be applied by the velocity
     * @return returns the new point (with the new location)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}