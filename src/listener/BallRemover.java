package listener;

import collidable.Block;
import game.GameLevel;
import shapes.Ball;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Creates listener.BallRemover for a game which contains balls listener.Counter.
     * @param game - the game.
     * @param ballsCounter - the balls listener.Counter
     */
    public BallRemover(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.remainingBalls = ballsCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}