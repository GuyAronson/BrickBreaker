package arkanoid.logics;

import arkanoid.observer.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator is in charge of displaying the updated score.
 */
public class ScoreIndicator implements Sprite {

    //Members:
    private final Counter score;

    /**
     * Construcor for the score indicator that will display the score.
     * @param scoreToBeDisplayed - the reference for the game's score.
     */
    public ScoreIndicator(Counter scoreToBeDisplayed) {
        this.score = scoreToBeDisplayed;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, GameLevel.WIDTH, GameLevel.BOUNDARY_SIZE);
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.BOUNDARY_SIZE);
        d.setColor(Color.BLACK);
        String str = "Score: " + this.score.getValue();
        d.drawText(GameLevel.WIDTH / 2, (int) (GameLevel.BOUNDARY_SIZE / 1.25), str, 15);
    }

    @Override
    public void timePassed() {
    }
}
