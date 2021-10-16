package arkanoid.logics;

import biuoop.DrawSurface;

/**
 * @author Guy Aronson 318654225.
 * This interface will be implement by the game's object
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d - the surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed and run any command.
     */
    void timePassed();
}