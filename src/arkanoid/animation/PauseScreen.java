package arkanoid.animation;

import biuoop.DrawSurface;

/**
 * A instance animation for pause screen.
 * @author Guy Aronson.
 */
public class PauseScreen implements Animation {

    //Members:
    private final boolean stop;

    /**
     * Constructor for the pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 3, d.getHeight() / 4, "Paused!", 75);
        d.drawText(d.getWidth() / 4, d.getHeight() / 2, "Press space to continue...", 35);
        d.drawText((int) (d.getWidth() / 2.25), (int) (d.getHeight() / 1.8), "Good Luck (;", 15);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}