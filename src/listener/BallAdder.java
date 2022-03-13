package listener;

import collidable.Block;
import game.GameLevel;
import shapes.Ball;
import shapes.Point;
import shapes.Velocity;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class BallAdder implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Creates listener.BallAdder for a game which contains balls listener.Counter.
     * @param game - the game.
     * @param ballsCounter - counter for the balls.
     */
    public BallAdder(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.remainingBalls = ballsCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.increase(1);
        Ball ball = new Ball(new Point(hitter.getX(), hitter.getY()), hitter.getSize(), hitter.getColor());
        Velocity v = new Velocity(-1 * hitter.getVelocity().getDx(), -1 * hitter.getVelocity().getDy());
        ball.setVelocity(v);
        ball.addToGame(this.game);
        ball.setGameEnvironment(hitter.getGame());
    }
}
