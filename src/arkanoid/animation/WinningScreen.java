package arkanoid.animation;

import arkanoid.observer.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A instance animation for the Winning Screen.
 * @author Guy Aronson.
 */
public class WinningScreen implements Animation {

    //Members:
    private boolean stop;
    private final int finalScore;

    /**
     * Constructor for the Winning Screen.
     * @param score - the score to be displayed.
     */
    public WinningScreen(Counter score) {
        this.stop = false;
        this.finalScore = score.getValue();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.RED);
        d.drawText((int) (d.getWidth() / 4), (int) (d.getHeight() / 3), "You Won!", 100);
        d.setColor(Color.BLACK);
        d.drawText((int) (d.getWidth() / 3), (int) (d.getHeight() / 2),
                "Your Score is " + this.finalScore, 35);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}