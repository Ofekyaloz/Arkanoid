package sprite;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class SpriteCollection {
    private java.util.List<Sprite> list;

    /**
     * creates a list of sprites.
     */
    public SpriteCollection() {
        this.list = new ArrayList<>();
    }

    /**
     * adds a sprite to the list.
     * @param s - the sprite.
     */
    public void addSprite(Sprite s) {
        list.add(s);
    }

    /**
     * calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<>(this.list);
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * calls drawOn(d) on all sprites.
     * @param d - the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteList = new ArrayList<>(this.list);
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }

    /**
     * Removes a sprite.Sprite from the list.
     * @param s - the sprite.Sprite.
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }
}