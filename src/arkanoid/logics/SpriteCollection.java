package arkanoid.logics;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy Aronson, 318654225
 * This class will hold a collection of sprites,
 * and has different methods to execute for these sprites.
 */
public class SpriteCollection {

    //Members:
    private final List<Sprite> sprites;

    /**
     * Constructor for the sprite collection,
     * will create an array list for the sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Method to add sprite to the collection.
     * @param s - a single sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Method to add sprites to the collection.
     * @param collidables - a list of blocks to add to the sprite collection.
     */
    public void addSprites(List<Collidable> collidables) {
        for (int i = 0; i < collidables.size(); i++) {
            this.sprites.add((Sprite) collidables.get(i));
        }

    }

    /**
     * Accessor.
     * @return - returns the collection, the sprites list.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     * @param d - the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
}