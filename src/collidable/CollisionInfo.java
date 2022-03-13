package collidable;

import shapes.Point;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class CollisionInfo {
    private Point p;
    private Collidable c;

    /**
     * saves the collision info - the point and the object.
     * @param p - the collision point.
     * @param c - the collidable object.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.p = p;
        this.c = c;
    }

    // the point at which the collision occurs.

    /**
     * @return - returns the collision point.
     */
    public Point collisionPoint() {
        return this.p;
    }

    // the collidable object involved in the collision.

    /**
     * @return - returns the collision object.
     */
    public Collidable collisionObject() {
        return this.c;
    }
}