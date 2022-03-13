package listener;

import collidable.Block;
import shapes.Ball;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor of listener.ScoreTrackingListener which contains listener.Counter for the score.
     * @param scoreCounter - the counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}