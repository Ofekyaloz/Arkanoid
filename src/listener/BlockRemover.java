package listener;

import collidable.Block;
import game.GameLevel;
import shapes.Ball;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor of listener.BlockRemover which contains the game and listener.Counter for the blocks.
     * @param game - the game.
     * @param removedBlocks - counter of the blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * removes the blocks - decrease the counter and remove the listener from the block that removed.
     * @param beingHit - the blocks that took the hit.
     * @param hitter - the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}