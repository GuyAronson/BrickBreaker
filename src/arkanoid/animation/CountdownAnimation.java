package arkanoid.animation;

import arkanoid.logics.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Animation that runs the countdown screen before the game starts.
 */
public class CountdownAnimation implements Animation {

    //Members:

    //The time for the countdown - which means for how long the countdown will appear.
    private final double secondsTimer;

    //Counter for the countdown, decrements from the first number till 0.
    private int count;

    // represents the amount of seconds to be displayed.
    private final int amountOfSeconds;
    private final SpriteCollection screen;
    private final Sleeper sleeper;

    /**
     * The CountdownAnimation will display the given gameScreen,
     * for numOfSeconds seconds, and on top of them it will show
     * a countdown from countFrom back to 1, where each number will
     * appear on the screen for (numOfSeconds / countFrom) seconds, before
     * it is replaced with the next one.
     * @param countFrom - the number of seconds to count from till 1.
     * @param timer - the number of seconds to count.
     * @param gameScreen - the sprites which will be displayed on the screen.
     */
    public CountdownAnimation(double timer, int countFrom, SpriteCollection gameScreen) {
        this.secondsTimer = timer;
        this.amountOfSeconds = countFrom;
        this.count = countFrom;
        this.screen = gameScreen;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.screen.drawAllOn(d);

        //Calculating the seconds for each number.
        long sleepingTime = (long) ((this.secondsTimer * 1000) / this.amountOfSeconds);
        d.setColor(Color.GRAY);
        d.drawText((int) (d.getWidth() / 2.3), (int) (d.getHeight() / 2.1), "" + this.count, 200);

        //Setting the sleep only after the first count appearance.
        if (this.count <= this.secondsTimer) {
            this.sleeper.sleepFor(sleepingTime);
        }
        this.count = this.count - 1;
    }

    @Override
    public boolean shouldStop() {
        return this.count < 0;
    }
}