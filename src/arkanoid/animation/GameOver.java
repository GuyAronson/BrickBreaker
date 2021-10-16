package arkanoid.animation;

import arkanoid.observer.Counter;
import biuoop.DrawSurface;

/**
 * A instance animation for the GameOver screen.
 * @author Guy Aronson.
 */
public class GameOver implements Animation {

    //Members:
    private boolean stop;
    private final int finalScore;

    /**
     * Constructor for the GameOver screen.
     * @param score - the score to be displayed.
     */
    public GameOver(Counter score) {
        this.stop = false;
        this.finalScore = score.getValue();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText((int) (d.getWidth() / 6), (int) (d.getHeight() / 3), "Game Over!", 100);
        d.drawText((int) (d.getWidth() / 3), (int) (d.getHeight() / 2),
                "Your Score is " + this.finalScore, 35);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}