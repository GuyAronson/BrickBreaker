package arkanoid.gameobjects;

import arkanoid.logics.GameLevel;
import arkanoid.logics.Sprite;
import arkanoid.logics.Velocity;
import arkanoid.logics.Collidable;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Guy Aronson - 318654225
 * A class for the paddle - the player moving it
 */
public class Paddle implements Sprite, Collidable {

    //Members
    private final biuoop.KeyboardSensor keyboard;
    private final Rectangle rect;

    //Constants:
    private static final double WIDTH = 100;
    private static final double HEIGHT = 40;

    /**
     * A constructor for the paddle object.
     * @param rec - the rectangle represents the paddle on the screen with its details.
     * @param board - the keyboard sensor
     */
    public Paddle(Rectangle rec, KeyboardSensor board) {
        this.rect = rec;
        this.keyboard = board;
    }

    /**
     * Another constructor for the paddle object - calling the main constructor.
     * @param upperLeft - the rectangle's upper left point
     * @param board - the keyboard sensor
     */
    public Paddle(Point upperLeft, KeyboardSensor board) {
        this(new Rectangle(upperLeft, WIDTH, HEIGHT), board);
    }

    /**
     * Method to move the paddle to the left.
     * decreasing 10 from the current rectangle's upper left X.
     */
    public void moveLeft() {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        double startScreen = 0;
        double move = 10;

        if (x > startScreen + GameLevel.BOUNDARY_SIZE) {
            this.rect.setUpperLeft(x - move, y);
        }
    }

    /**
     * Method to move the paddle to the right.
     * adding 10 to the current rectangle's upper left X
     */
    public void moveRight() {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        double move = 10;
        if (x + this.rect.getWidth() < GameLevel.WIDTH - GameLevel.BOUNDARY_SIZE) {
            this.rect.setUpperLeft(x + move, y);
        }
    }

    /**
     * The Class implements the sprite interface, thus implements the timePassed method.
     * the method checks whether a left or right arrow key pressed.
     * calls the relevant method.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Method to draw the paddle on the board.
     * @param surface - the surface to draw the paddle on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.ORANGE);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * The class implements collidable interface and implements this accessor method.
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * The hit method get a collision point and object and compute the new velocity.
     * @param collisionPoint - the collision point that is anticipated.
     * @param currentVelocity - the current velocity of the object that will hit at the collision point.
     * @param hitter - the ball that hits the block.
     * @return a new velocity instance for the ball after the collision happened.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //Variables:
        int amountOfRegions = 5, angle = 300, angleGap = 30;
        double collisionX = collisionPoint.getX(), collisionY = collisionPoint.getY(),
        regionSize = this.rect.getWidth() / amountOfRegions;

        //the speed is the square of dx^2 + dy^2
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        final double epsilon = Math.pow(10, -3);
        double[] regions = new double[amountOfRegions];

        for (int i = 0; i < regions.length; i++) {
            regions[i] = regionSize * (i + 1);
        }

        //Checks if the collision point is on the top/bottom side of the block.
        if (Math.abs(this.rect.getUpperLeft().getY() - collisionY) < epsilon) {
            for (int i = 0; i < regions.length; i++) {
                if (collisionX < this.rect.getUpperLeft().getX() + regions[i]) {
                    return Velocity.fromAngleAndSpeed(angle + angleGap * i, speed);
                }
            }
        }

        //Checks if the collision point is on the left/right side of the block.
        if (Math.abs(this.rect.getUpperLeft().getX() - collisionX) < epsilon
            || Math.abs((this.rect.getUpperLeft().getX() + this.rect.getWidth()) - collisionX) < epsilon) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * Method to add the paddle to the game.
     * @param g - the game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * A boolean method that checks whether the ball got inside the paddle.
     * @param b - the ball object.
     * @return true if the ball is inside the paddle, false otherwise.
     */
    public boolean isBallInside(Ball b) {
        double ballX = b.getX(), ballY = b.getY();
        double rectX = this.rect.getUpperLeft().getX(), rectY = this.rect.getUpperLeft().getY();
        double height = this.rect.getHeight(), width = this.rect.getWidth();

        if (ballX > rectX &&  ballX < rectX + width) {
            if (ballY > rectY && ballY < rectY + height) {
                return true;
            }
        }
        return false;
    }
}