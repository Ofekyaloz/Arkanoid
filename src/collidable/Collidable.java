package collidable;

import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public interface Collidable {

    /**
     * @return - Returns the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Returns the new velocity expected after the hit.
     * @param hitter - the ball that hit.
     * @param collisionPoint - the collisionPoint of two lines.
     * @param currentVelocity - the velocity of the ball.
     * @return - the new shapes.Velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}