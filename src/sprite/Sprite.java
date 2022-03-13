package sprite;

import biuoop.DrawSurface;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public interface Sprite {

    /**
     * draws the sprite to the screen.
     * @param d - the draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}