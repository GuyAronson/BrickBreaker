package arkanoid.observer;
import arkanoid.logics.GameLevel;
import arkanoid.logics.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * LevelName is in charge of displaying the level's name.
 */
public class LevelName implements Sprite {

    //Members:
    private final String name;

    /**
     * Construcor for the LevelName that will display the name.
     * @param levelName - the level's name.
     */
    public LevelName(String levelName) {
        this.name = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) (GameLevel.WIDTH / 1.2), (int) (GameLevel.BOUNDARY_SIZE / 1.25), this.name, 15);
    }

    @Override
    public void timePassed() {
    }
}
