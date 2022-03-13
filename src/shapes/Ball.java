package shapes;

import biuoop.DrawSurface;
import collidable.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import sprite.Sprite;
import java.awt.Color;
import java.util.Random;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Ball implements Sprite {
    private static final int ZERO = 0, ONE = 1, TWO = 2, SIX = 6, TEN = 10, DEGREES = 180, DEFAULT_HIGH = 200,
            DEFAULT_WIDTH = 200;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point e1, e2;
    private GameEnvironment game;

    /**
     * sets balls values and a default velocity.
     * @param center - the center point of the ball.
     * @param r - the size of the radius.
     * @param color - the ball's color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(ONE, ONE);
        this.game = new GameEnvironment();
    }

    /**
     * @param x - x value of the center point.
     * @param y - y value of the center point.
     * @param r - the size of the radius.
     * @param color - the ball's color.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this (new Point(x, y), r, color);
    }

    /**
     * sets the limits of the surface.
     * @param startX - the x value of the start of the surface.
     * @param startY - the y value of the start of the surface.
     * @param endX - the x value of the end of the surface.
     * @param endY - the y value of the end of the surface.
     */
    public void setEdges(int startX, int startY, int endX, int endY) {
        this.e1 = new Point(startX, startY);
        this.e2 = new Point(endX, endY);
    }

    // accessors
    /**
     * @return - x value of the center point.
     */
    public double getX() {
        return ((int) this.center.getX());
    }

    /**
     * @return - y value of the center point.
     */
    public double getY() {
        return ((int) this.center.getY());
    }

    /**
     * @return - the size of the radius.
     */
    public int getSize() {
        return r;
    }

    /**
     * @return - the color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * @return - the start point of the surface.
     */
    public Point getStartEdge() {
        return this.e1;
    }

    /**
     * @return - the end point of the surface.
     */
    public Point getEndEdge() {
        return this.e2;
    }

    /**
     * checks if this ball's center is on the surface, if not sets a new center.
     */
    public void checkStartPoint() {
        Point start = new Point((this.getStartEdge().getX() + this.getEndEdge().getX()) / TWO,
                (this.getStartEdge().getY() + this.getEndEdge().getY()) / TWO);
        if (this.getX() - getSize() <= this.getStartEdge().getX()
                || this.getX() + getSize() >= this.getEndEdge().getX()) {
            this.center = start;
        } else if (this.getY() - getSize() <= this.getStartEdge().getY()
                || this.getY() + getSize() >= this.getEndEdge().getY()) {
            this.center = start;
        }
    }

    /**
     * @param surface - draws the ball on the given DrawSurface.
     */
    public void drawOn(DrawSurface surface) {

        // checks if at the beginning the ball is in the zone.
        if (this.getSize() * TWO >= surface.getWidth() || this.getSize() * TWO >= surface.getHeight()
                || (this.getStartEdge() != null && this.getEndEdge() != null
                && (this.getSize() * TWO > this.getEndEdge().getX() - this.getStartEdge().getX()
                || this.getSize() * TWO > this.getEndEdge().getY() - this.getStartEdge().getY()))) {
            r = TEN;
            double angel, speed;
            Random rand = new Random();
            speed = SIX;
            angel = rand.nextInt(DEGREES);
            Velocity v1 = Velocity.fromAngleAndSpeed(angel, speed);
            this.setVelocity(v1);
        }
        surface.setColor(getColor());
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
        surface.setColor(Color.black);
        surface.drawCircle((int) center.getX(), (int) center.getY(), r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * sets this shapes.Velocity to be v.
     * @param v1 - the new value of shapes.Velocity.
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * sets this shapes.Velocity with the new values of dx and dy.
     * @param dx - dx value.
     * @param dy - dy value.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return - this velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     *  Moves the ball on step according to the velocity.
     *  if the ball touched the boundaries, the velocity of the ball changes to the opposite
     *  direction.
     */
    public void moveOneStep() {
        Line trajectory = this.trajectory();
        CollisionInfo info = this.getGame().getClosestCollision(trajectory);
        if (info != null) {
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Calculate the ball trajectory - the next center.
     * @return - the trajectory.
     */
    private Line trajectory() {
        double nextX, nextY;
        if (this.getVelocity().getDx() > ZERO) {
            nextX = this.getX() + this.getSize();
        } else if (this.getVelocity().getDx() < ZERO) {
            nextX = this.getX() - this.getSize();
        } else {
            nextX = this.getX();
        }
        if (this.getVelocity().getDy() > ZERO) {
            nextY = this.getY() + this.getSize();
        } else if (this.getVelocity().getDy() < ZERO) {
            nextY = this.getY() - this.getSize();
        } else {
            nextY = this.getY();
        }
        return new Line(this.getX(), this.getY(), nextX, nextY);
    }

    /**
     * returns the game.GameEnvironment of this ball.
     * @return - the game.GameEnvironment.
     */
    public GameEnvironment getGame() {
        return this.game;
    }

    /**
     * sets the ball game.GameEnvironment.
     * @param g - the game.GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.game = g;
    }

    /**
     * adds this ball to the game.
     * @param g - the game.Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removes this ball from the game.
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

