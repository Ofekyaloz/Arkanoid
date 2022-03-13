package collidable;

import biuoop.DrawSurface;
import game.GameLevel;
import listener.HitListener;
import listener.HitNotifier;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
import sprite.Sprite;
import shapes.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final int MINUS_ONE = -1;
    private Rectangle rectangle;
    private List<HitListener> hitListeners;


    /**
     * the constructor of the class, sets this block rectangle and sets a boolean variable if the block is hit.
     * @param rect - the shape of the block.
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * creates a rectangle and calls to the other constructor.
     * @param upperLeft - the left point on top of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Block(Point upperLeft, double width, double height) {
        this(new Rectangle(upperLeft, width, height));
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = null;
        if (rectangle.getUpperSide().isOnLine(collisionPoint) || rectangle.getLowerSide().isOnLine(collisionPoint)) {
            v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * MINUS_ONE);
        }
        if (rectangle.getLeftSide().isOnLine(collisionPoint) || rectangle.getRightSide().isOnLine(collisionPoint)) {
            if (v != null) {
                v = new Velocity(currentVelocity.getDx() * MINUS_ONE, v.getDy());
            } else {
                v = new Velocity(currentVelocity.getDx() * MINUS_ONE, currentVelocity.getDy());
            }
        }
        this.notifyHit(hitter);
        if (v != null) {
            return v;
        }
        return currentVelocity;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        this.rectangle.drawnOn(d);
    }

    @Override
    public void timePassed() {

    }

    /**
     * adds this block into a game as a collidable.Collidable and sprite.Sprite object.
     * @param g - the game that we added the block into
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Removes this block from the game.
     * @param game - the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify to the listeners that the block got hit.
     * @param hitter - the ball that hit this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
