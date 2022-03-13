package game;

import collidable.Collidable;
import collidable.CollisionInfo;
import shapes.Line;
import shapes.Point;
import java.util.ArrayList;
import java.util.List;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class GameEnvironment {
    private java.util.List<Collidable> list;

    /**
     * creates an array list that contains the collidable.Collidable objets.
     */
    public GameEnvironment() {
            this.list = new ArrayList<>();
    }

    /**
     * adds the given collidable to the environment.
     * @param c - a collidable object.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * checks if the trajectory intersection with one of the collisions, if it does
     * it checks what is the closest collision object and returns the point of the intersection
     * and the object that contains the point - the collidable.CollisionInfo.
     * If the trajectory doesn't intersection with any of the collisions, returns null.
     * @param trajectory - the line we check if it intersection with one of the objects.
     * @return - the collidable.CollisionInfo - point and the collidable object if exists else returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point hitPoint = null;
        Collidable collidable = null;
        List<Collidable> collidableList = new ArrayList<>(this.list);
        for (Collidable c: collidableList) {
            Point tmpHitPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (hitPoint == null && tmpHitPoint != null) {
                hitPoint = tmpHitPoint;
                collidable = c;
            } else if (tmpHitPoint != null) {

                // checks witch line is closer
                if (trajectory.start().distance(tmpHitPoint) <= trajectory.start().distance(hitPoint)) {
                    hitPoint = tmpHitPoint;
                    collidable = c;
                }
            }
        }
        if (hitPoint != null) {
            return new CollisionInfo(hitPoint, collidable);
        }
        return null;
    }

    /**
     * removes the collidable.Collidable object from the list of collidable.Collidable objects of this gameEnvironment.
     * @param c - the collidable.Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }
}