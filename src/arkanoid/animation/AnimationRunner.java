package arkanoid.animation;
import arkanoid.logics.GameLevel;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner will be in charge of running any animation it gets.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * A constuctor for the animation runner.
     */
    public AnimationRunner() {
        framesPerSecond = 70;
        this.gui = new GUI("Arkanoid Game", GameLevel.WIDTH, GameLevel.HEIGHT);
        sleeper = new Sleeper();
    }

    /**
     * Accessor for the GUI object.
     * @return the GUI object.
     */
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * The Method closes the gui when the game stops.
     */
    public void closeGUI() {
        this.gui.close();
    }

    /**
     * The run method will run the animation.
     * @param animation - the animation instace to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}