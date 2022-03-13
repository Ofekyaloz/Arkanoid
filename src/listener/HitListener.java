package listener;

import collidable.Block;
import shapes.Ball;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the shapes.Ball that's doing the hitting.
     * @param beingHit - the block that took the hit.
     * @param hitter - the ball that hits the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}