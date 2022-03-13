package animation;

import biuoop.DrawSurface;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public interface Animation {
    /**
     * Draws the animation for one frame.
     * @param d - the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     * @return - true
     */
    boolean shouldStop();
}
