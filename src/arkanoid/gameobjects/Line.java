// Guy Aronson - 318654225
package arkanoid.gameobjects;

import java.util.List;
/**
 * Class for line object.
 * @author Guy Aronson
 */
public class Line {

    //Members
    private final Point start;
    private final Point end;
    private double incline;
    private double elevation;

    //Constants:
    static final int NO_INCLINE = 0;

    /**
     * Constructor for the line object.
     * @param p1 - The line's start point
     * @param p2 - The line's end point
     */
    public Line(Point p1, Point p2) {
        this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * Another constructor for the line object.
     * @param x1 - The start point's X
     * @param y1 - The start point's Y
     * @param x2 - The end point's X
     * @param y2 - The end point's Y
     */
    public Line(double x1, double y1, double x2, double y2) {

        //checks and inserts the lower point to the start.
        if (x1 < x2) {
            //First point
            this.start = new Point(x1, y1);
            //Second point
            this.end = new Point(x2, y2);
        } else if (x1 > x2) {
            //First point
            this.start = new Point(x2, y2);
            //Second point
            this.end = new Point(x1, y1);
        } else {

            //The x coordinate is equal in both of the points.
            if (y1 < y2) {
                this.start = new Point(x1, y1);
                this.end = new Point(x2, y2);
            } else {
                this.start = new Point(x2, y2);
                this.end = new Point(x1, y1);
            }
        }
        this.setIncline();
        this.setElevation();
    }

    /**
     * Method to calculates the length of a the line.
     * The start point calls the distance method using the end point as
     * parameter.
     * @return The distance between 2 numbers
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * setIncline method calculates the line's incline, and inserts it into
     * the incline member.
     * This is a private method it's used only within the class.
     */
    private void setIncline() {

        /*
        Checks if the line's equation form is 'x=a'.
        which means the X values are the same
         */
        if (this.start.getX() == this.end.getX()) {

            //Checks if the start and the end points are the same point - (if the line is actually a point)
            if (this.start.getY() == this.end.getY()) {
                this.incline = 0;
            } else {
                this.incline = Double.POSITIVE_INFINITY;
            }
        } else {

            // (y2-y1)/(x2-x1) = m
            this.incline = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        }
    }

    /**
     * This method calculates the elevation of the line's equation.
     * which means the 'b' of 'y=mx+b'
     */
    private void setElevation() {

        /*  line equation is: y-y1=m(x-x1)
            which equals to : y = mx+(-x1m+y1)
            we calculates the 'b' of the line equation 'y=mx+b'.*/
        if (this.incline == Double.POSITIVE_INFINITY) {
            this.elevation = 0;
        } else {
            this.elevation = (this.incline * (-this.start.getX())) + this.start.getY();
        }
    }

    /**
     * middle method calculates the middle point of the line.
     * @return returns the middle point.
     */
    public Point middle() {

        //calculates the mid point's coordinates
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;

        //Creating new point
        Point mid = new Point(midX, midY);
        return mid;
    }

    /**
     * Accessor.
     * getStart method returns the start point
     * @return start point
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Accessor.
     * getEnd method retuns the end point
     * @return the end point
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Accessor.
     * Method returns the incline of the line
     * @return this line's incline.
     */
    public double getIncline() {
        return this.incline;
    }

    /**
     * Accessor.
     * Method returns the elevation of the line
     * @return this line's elevation.
     */
    public double getElevation() {
        return this.elevation;
    }

    /**
     * This method checks whether a point is on the line - between the end and the start points.
     * @param p - any point to check if it's on the line.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isInRange(Point p) {

        /*Checks equality between the Y coordinate and the equation mx+b , should be less than epsilon*/
        if (this.incline == Double.POSITIVE_INFINITY
                || Math.abs(p.getY() - ((p.getX() * this.incline) + this.elevation)) < Point.EPSILON) {

            /*checks if the point is within the x range:
            * bigger than the start X and smaller than the end X or equals to one of them
            * Checks equals with the epsilon*/
            if ((p.getX() < this.end.getX() || Math.abs(p.getX() - this.end.getX()) < Point.EPSILON)
                    && (p.getX() > this.start.getX() || Math.abs(p.getX() - this.start.getX()) < Point.EPSILON)) {

                /*Now we need to check if the point is within the y range (depends on the incline)
                * Equality test - using the epsilon.*/
                if (this.incline >= NO_INCLINE) {
                    return (p.getY() > this.start.getY()
                                || Math.abs(p.getY() - this.start.getY()) < Point.EPSILON)
                            && (p.getY() < this.end.getY()
                                || Math.abs(p.getY() - this.end.getY()) < Point.EPSILON);
                } else {
                    return (p.getY() < this.start.getY()
                                || Math.abs(p.getY() - this.start.getY()) < Point.EPSILON)
                            && (p.getY() > this.end.getY()
                                || Math.abs(p.getY() - this.end.getY()) < Point.EPSILON);
                }
            }
        }
        return false;
    }

    /**
     * This method is the same as above , just with different arguments - checks whether a point is within
     * the line.
     * The method calling the original method.
     * @param x - point's X coordinate
     * @param y - point's Y coordinate
     * @return true if the point is within the line.
     */
    public boolean isInRange(double x, double y) {
        return this.isInRange(new Point(x, y));
    }

    /**
     * Method that checks if a line is intersecting with another line while at least one of them has
     * an infinity incline.
     * @param other - another line to compare with
     * @return true if the lines are intersecting, false otherwise.
     */
    private boolean isIntersectingInfinityElevation(Line other) {
        double x;
        double diffY;

        //Checks if both of the line are with infinity incline- which means parallel lines
        if (this.incline == Double.POSITIVE_INFINITY && other.getIncline() == Double.POSITIVE_INFINITY) {

            //First checks if the lines have the same X coordinate
            if (this.start.getX() == other.getStart().getX()) {

                // Parallel lines will be intersecting if the start/end points within the other line.
                if ((this.start.getY() >= other.getStart().getY() && this.start.getY() <= other.getEnd().getY())
                    || (this.end.getY() >= other.getStart().getY() && this.end.getY() <= other.getEnd().getY())) {
                    return true;
                } else if ((other.getStart().getY() >= this.start.getY()
                        && other.getStart().getY() <= this.end.getY())
                    || (other.getEnd().getY() >= this.start.getY()
                        && other.getEnd().getY() <= this.end.getY())) {
                    return true;
                }
            }
        }

        //Now we check each case - if one of them is with infinity incline.
        if (this.incline == Double.POSITIVE_INFINITY) {

            //Gets the coordinates by the other line.
            x = this.start.getX();
            diffY = (other.getIncline() * x) + other.getElevation();

            /* Checks if this point is within the range of the infinity elevation line.
             * pays attention to the line's incline.*/
            if (other.getIncline() >= NO_INCLINE) {

                //Checks if it's in between these 2 lines.
                return this.isInRange(x, diffY) && other.isInRange(x, diffY);
                /*return (diffY >= this.start.getY() && diffY <= this.end.getY())
                        && (diffY >= other.getStart().getY() && diffY <= other.getEnd().getY());*/

            } else if (other.getIncline() < NO_INCLINE) {

                //Checks if it's in between these 2 lines.
                return this.isInRange(x, diffY) && other.isInRange(x, diffY);
                /*return (diffY >= this.start.getY() && diffY <= this.end.getY())
                        && (diffY <= other.getStart().getY() && diffY >= other.getEnd().getY());*/
            }

        } else if (other.getIncline() == Double.POSITIVE_INFINITY) {
            x = other.getStart().getX();
            diffY = (this.incline * x) + this.elevation;

            /* Checks if this point is within the range of the infinity elevation line.
             * pays attention to the line's incline.*/
            if (this.incline >= NO_INCLINE) {

                //Checks if it's in between these 2 lines.
                return this.isInRange(x, diffY) && other.isInRange(x, diffY);
                /*return (diffY >= this.start.getY() && diffY <= this.end.getY())
                        && (diffY >= other.getStart().getY() && diffY <= other.getEnd().getY());*/

            } else if (this.incline < NO_INCLINE) {

                //Checks if it's in between these 2 lines.
                return this.isInRange(x, diffY) && other.isInRange(x, diffY);
                /*return (diffY <= this.start.getY() && diffY >= this.end.getY())
                        && (diffY >= other.getStart().getY() && diffY <= other.getEnd().getY());*/
            }

        }
        return false;
    }

    /**
     * method that checks whether 2 lines intersecting.
     * @param other - another line object.
     * @return boolean value, true if thet are intersecting, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double x, y;

        // Checks intersection with an infinity incline
        if (this.incline == Double.POSITIVE_INFINITY || other.getIncline() == Double.POSITIVE_INFINITY) {
            return this.isIntersectingInfinityElevation(other);
        }

        //Checks if one of the lines is a point.
        if (this.start.equals(this.end)) {
            return other.isInRange(this.start.getX(), this.start.getY());
        } else if (other.getStart().equals(other.getEnd())) {
            return this.isInRange(other.getStart().getX(), other.getStart().getY());
        }

        //Parallel lines - will be intersecting if the start/end points within the other line.
        if (other.getIncline() == this.incline) {

            if (this.isInRange(other.getStart()) || this.isInRange(other.getEnd())) {
                return true;
            } else if (other.isInRange(this.getStart()) || other.isInRange(this.getEnd())) {
                return true;
            }
        }

        /* calculates the intersection point's X by this equation:
         * y=ax+b  ,  y=mx+n
         * mx+n = ax+b
         * (m-a)x = b-n */
        x = (this.getElevation() - other.getElevation()) / (other.getIncline() - this.getIncline());
        y = (this.incline * x) + this.elevation;

        /*Checks whether the intersection point's coordinates are between the
         coordinates of both line's points (between the starts and ends
         points)*/
        return this.isInRange(x, y) && other.isInRange(x, y);
    }

    /**
     * method that calculates the intersection point and returns it.
     * @param other - another line object
     * @return Point object of the intersection point, returns null if the
     * line are  not intersecting.
     */
    public Point intersectionWith(Line other) {
        double x, y;

        //First we check if the lines intersected
        if (this.isIntersecting(other)) {

            //Parallel line will not be intersected.
            if (other.getIncline() == this.incline) {

                //Checks if one of the lines is a point
                if (this.start.equals(this.end)) {
                    return this.start;
                } else if (other.getStart().equals(other.getEnd())) {
                    return other.getStart();
                } else {
                    if (other.getStart().equals(this.end)) {
                        return this.end;
                    } else if (this.start.equals(other.getEnd())) {
                        return this.start;
                    } else {
                        return null;
                    }
                }
            } else if (this.incline == Double.POSITIVE_INFINITY) {
                x = this.start.getX();
                y = (other.getIncline() * x) + other.getElevation();
            } else if (other.getIncline() == Double.POSITIVE_INFINITY) {
                x = other.getStart().getX();
                y = (this.incline * x) + this.elevation;
            } else {
                x = (this.getElevation() - other.getElevation()) / (other.getIncline() - this.getIncline());
                y = (this.incline * x) + this.elevation;
            }
            return new Point(x, y);
        }
        return null;
    }

    /**
     * equals method checks whether this line equals to another line (given
     * as parameter).
     * @param other - another line
     * @return boolean value - true if they are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end));
    }

    /**
     * Checks intersection points with a rectangle.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect - the rectangle to check intersections with
     * @return null if they are not intersect otherwise returns an intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point closest = null;
        double distance, minDistance = Double.POSITIVE_INFINITY;
        if (intersectionPoints.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < intersectionPoints.size(); i++) {
                distance = this.start.distance(intersectionPoints.get(i));
                if (distance <= minDistance) {
                   minDistance = distance;
                   closest = intersectionPoints.get(i);
                }
            }
        }
        return closest;
    }
}
