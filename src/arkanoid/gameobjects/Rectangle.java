//Guy Aronson -318654225
package arkanoid.gameobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * A class represents the rectangles in the arkanoid game.
 * @author Guy Aronson
 */
public class Rectangle {

    //Members:
    private Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructor for rectangle instances - creates a rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle
     * @param width - the rectangle's width
     * @param height - the rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line the line which we check intersection points
     * @return a list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {

        Line[] recSides = this.getSides();

        //Creating the list:
        List<Point> intersectionPoints =  new ArrayList<>();

        //Check each rectangle's side whether it intersects with the given line.
        for (int i = 0; i < recSides.length; i++) {

            Point p = line.intersectionWith(recSides[i]);
            boolean isAlreadyIn = false;
            if (p != null) {

                //Checks if the point is not already in the list - avoiding duplicates.
                for (int j = 0; j < intersectionPoints.size(); j++) {
                    if (p.equals(intersectionPoints.get(j))) {
                        isAlreadyIn = true;
                        break;
                    }
                }

                //If the point is not on the list - adding it
                if (!isAlreadyIn) {
                    intersectionPoints.add(p);
                }
            }
        }
        return intersectionPoints;
    }

    /**
     * Accessor for the rectangle's width.
     * @return the rectangle's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Accessor for the rectangle's height.
     * @return the rectangle's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Accessor for the rectangle's upper left point.
     * @return the rectangle's upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Setter - suppose to move the rectangle on the screen.
     * @param p - new Point for the upper left of the rectangle
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * Setter - suppose to move the rectangle on the screen.
     * @param x - X coordinate of the upper left point
     * @param y - Y coordinate of the upper left point
     */
    public void setUpperLeft(double x, double y) {
        this.setUpperLeft(new Point(x, y));
    }


    /**
     * Method that creates a list of lines (each line is each side of the rectangle).
     * @return returns the list of the sides.
     */
    public Line[] getSides() {
        //Creating points that represent the rectangle's vertexes.
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

        //Creating lines that represent the sides of the rectangle;
        Line upperSide = new Line(this.upperLeft, upperRight);
        Line rightSide = new Line(upperRight, bottomRight);
        Line leftSide = new Line(this.upperLeft, bottomLeft);
        Line bottomSide = new Line(bottomLeft, bottomRight);

        //Declaring an array for the rectangle's sides
        Line[] recSides = {upperSide, bottomSide, leftSide, rightSide};

        return recSides;
    }
}
