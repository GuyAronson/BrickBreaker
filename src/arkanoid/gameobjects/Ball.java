// Guy Aronson - 318654225
package arkanoid.gameobjects;

import arkanoid.logics.Collidable;
import arkanoid.logics.GameEnvironment;
import arkanoid.logics.Sprite;
import arkanoid.logics.Velocity;
import arkanoid.logics.CollisionInfo;
import arkanoid.logics.GameLevel;

import java.awt.Color;

/**.
 *A class for Ball object.
 * @author Guy Aronson.
 */
public class Ball implements Sprite {

    //Members
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;

    //Constants:
    private static final int NO_VELOCITY = 0;

    /**
     * A constructor gets only center point, radius and color.
     * @param center - the center point of the ball
     * @param radius - the ball's radius
     * @param color - the ball's color
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(NO_VELOCITY, NO_VELOCITY);
        this.game = new GameEnvironment();
    }

    /**
     * Another default constructor gets center - X & Y, radius and color.
     * @param x - the center's X coordinate
     * @param y - the center's Y coordinate
     * @param radius - the ball's radius
     * @param color - the ball's color
     * this method will call its main constructor.
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * A constructor gets center point and radius - without a color.
     * @param center - the center point of the ball
     * @param radius - the ball's radius
     *  The color has a default value.
     */
    public Ball(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        this.color = Color.WHITE;
        this.velocity = new Velocity(NO_VELOCITY, NO_VELOCITY);
        this.game = new GameEnvironment();
    }

    /**
     * A constructor gets center point and radius - without a color.
     * @param x - the center's X coordinate
     * @param y - the center's Y coordinate
     * @param radius - the ball's radius
     * this method will call its main constructor.
     */
    public Ball(double x, double y, int radius) {
        this(new Point(x, y), radius);
    }

    //Accessors:
    /**
     * Accessor for the center's X coordinate - with casting to int.
     * @return center's X coordinate
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Accessor for the center's Y coordinate - with casting to int.
     * @return center's Y coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Accessor for the ball's radius.
     * @return the radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Accessor for the ball's color.
     * @return the ball's color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Accessor for the ball's velocity.
     * @return the ball's velocity instance.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Accessor.
     * @return the Game Environment
     */
    public GameEnvironment getEnvironment() {
        return this.game;
    }

    /**
     * The function draws the ball on the given surface by its color.
     * @param surface - the surface which the ball will be drawn on.
     */
    public void drawOn(biuoop.DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * setVelocity method sets the ball's velocity by the parameter.
     * @param v - velocity instance, will be set in the ball's velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setVelocity method sets new velocity into the ball's velocity.
     * @param dx - velocity's dx
     * @param dy - velocity's dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Set environment method, will change and adjust the environment for the ball.
     * @param newEnv - setting new environment for the ball.
     */
    public void setEnvironment(GameEnvironment newEnv) {
        this.game = newEnv;
    }

    /**
     * moveOneStep method, literally moves the ball one step by its velocity field.
     * but first it checks if the ball is inside the screen boundaries.
     */
    public void moveOneStep() {
        double dx = this.velocity.getDx(), dy = this.velocity.getDy();

        //Computing the trajectory:
        Line trajectory =  new Line(this.center, this.velocity.applyToPoint(this.center));

        //Checks if the ball is inside any block on the screen
        for (int i = 0; i < this.game.getEnvironment().size(); i++) {
            Collidable block = this.game.getEnvironment().get(i);

            //Checks if the ball is inside the block
            if (block.isBallInside(this)) {

                //Moving it backwards
                this.center = new Point(this.getX() - (2 * dx), this.getY() - (2 * dy));

                //Bouncing it back - changing the velocity
                this.bounceBack(block);

                //applying the velocity to the center of the ball.
                this.center = this.velocity.applyToPoint(this.center);

                return;
            }
        }

        //Checks if there is a collision.
        CollisionInfo collision = this.game.getClosestCollision(trajectory);

        //If there isn't
        if (collision !=  null) {
            double beforeHitX = getX(), beforeHitY = getY();

            //Set the ball location to "almost" touch the block ׂ(depends on the velocity - positive/negative)
            beforeHitX = collision.collisionPoint().getX() - dx;
            beforeHitY = collision.collisionPoint().getY() - dy;
            this.center = new Point(beforeHitX, beforeHitY);

            //Set the velocity by the "hit" - velocity calculator method.
            this.setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(),
                    this.velocity));
        }
        //applying the velocity to the center of the ball.
        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * Notify that time has passed and the ball needs to move.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * BounceBack is a method which activated when the ball is accidentally get into a block.
     * The method calculates the new velocity.
     * @param block - the block which the ball got into.
     */
    public void bounceBack(Collidable block) {
        boolean isOnLeft = false, isOnRight = false, isOnBottom = false, isOnTop = false;
        double dx = this.velocity.getDx(), dy = this.velocity.getDy(), x = this.center.getX(),
                y = this.center.getY(), rectX = block.getCollisionRectangle().getUpperLeft().getX(),
                rectY = block.getCollisionRectangle().getUpperLeft().getY(),
                rectWidth = block.getCollisionRectangle().getWidth(),
                rectHeight = block.getCollisionRectangle().getHeight();

        //Checks the location of the ball compared to the block:
        if (x >= rectX && x <= rectX + rectWidth) {

            //Now the ball is on top or bottom of the block
            if (y <= rectY && dy > 0) {
               isOnTop = true;
            } else if (y >= rectY + rectHeight && dy < 0) {
                isOnBottom = true;
            } else {
                return;
            }
        } else if (y >= rectY && y <= rectY + rectHeight) {

            //Now the ball is on left or right of the block
            if (x >= rectX + rectWidth && dx < 0) {
               isOnRight = true;
            } else if (x <= rectX && dx > 0) {
                isOnLeft = true;
            } else {
                return;
            }
        } else {
            isOnTop = true;
        }

        //Now we are bouncing back the ball based on his location:
        if (isOnBottom || isOnTop) {
            dy *= -1;
        } else if (isOnLeft || isOnRight) {
            dx *= -1;
        }

        this.setVelocity(dx, dy);
    }

    /**
     * A method to delete a ball from the game.
     * @param g - the game parameter.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}