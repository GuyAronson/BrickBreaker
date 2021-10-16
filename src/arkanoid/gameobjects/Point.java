// Guy Aronson - 318654225
package arkanoid.gameobjects;

/**
 * A class for Point object.
 * @author Guy Aronson.
 */
public class Point {

    //Members:
    private double x;
    private double y;

    //Epsilon variable - checks the equality approximation of the 2 points
    public static final double EPSILON = Math.pow(10, -10);

    /**
     * A constructor for the point class, sets the X and Y variables.
     * @param x - point's X
     * @param y - point's Y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance method calculates the distace between 2 points.
     * @param other - another point
     * @return the distance between 2 points
     */
    public double distance(Point other) {
        double xDistance = (this.x - other.x);
        double yDistance = (this.y - other.y);

        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    /**
     * equals method checks if another point given as parameter is the same.
     * point as the point calls this method
     * @param other - another point
     * @return boolean value - true if it's the same point, false otherwise.
     */
    public boolean equals(Point other) {
        if (Math.abs(this.x - other.getX()) < EPSILON) {
            if (Math.abs(this.y - other.getY()) < EPSILON) {
                return true;
            }
        }
        return false;
    }

    /**
     * getX method returns the point's X.
     * @return X
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY method returns the point's Y.
     * @return Y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Convert a point to string.
     * @return a string - ready to be printed.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
