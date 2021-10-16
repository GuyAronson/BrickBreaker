package arkanoid.animation;
import biuoop.DrawSurface;

/**
 * An interface to animate the game.
 */
public interface Animation {

    /**
     * Method that executes all of the commands requires to run a single frame.
     * @param d  - the surface we draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Method checks whether the game should stop or not.
     * @return boolean value - true if the game should stop, false otherwise.
     */
    boolean shouldStop();
}