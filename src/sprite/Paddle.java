package sprite;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidable.Collidable;
import game.GameLevel;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import shapes.Line;
import java.awt.Color;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Paddle implements Sprite, Collidable {
    private static final int HEIGHT = 15, PADDLE_Y = 575, LEFT_BOUND = 10,
            RIGHT_BOUND = 790, REGION1 = 300, REGION2 = 330, REGION4 = 30, REGION5 = 60, MINUS_ONE = -1;
    private int width, speed, paddleX;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int start, end;

    /**
     * Sets the paddle shape - the rectangle, the right and the left borders and the keyboard sensor.
     * @param key - the keyboard sensor.
     * @param width - the width of the paddle.
     * @param speed - the speed of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor key, int width, int speed) {
        this.width = width;
        this.paddleX = (800 - width) / 2;
        this.paddle = new Rectangle(new Point(this.paddleX, PADDLE_Y), this.width, HEIGHT);
        this.keyboard = key;
        this.start = LEFT_BOUND;
        this.end = RIGHT_BOUND;
        this.speed = speed;
    }

    /**
     * changes the paddle position - moves the rectangle to to left.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() > start) {
            this.paddle = new Rectangle(new Point(paddle.getUpperLeft().getX() - this.speed,
                    paddle.getUpperLeft().getY()), this.width, HEIGHT);
        }
    }

    /**
     * changes the paddle position - moves the rectangle to to right.
     */
    public void moveRight() {
        if (this.paddle.getRightSide().end().getX() < end) {
            this.paddle = new Rectangle(new Point(paddle.getUpperLeft().getX() + this.speed,
                    paddle.getUpperLeft().getY()), this.width, HEIGHT);
        }
    }

    /**
     * checks if the left key or the right key pressed - if it does,
     * moves the paddle to the right side.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * draw the paddle on the surface.
     * @param d - the draw surface
     */
    public void drawOn(DrawSurface d) {
        this.paddle.setColor(Color.WHITE);
        this.paddle.drawnOn(d);
    }

    /**
     * @return - the collision shape - rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * divides this paddle into 5 pieces when the balls hit each part it changes its velocity depends
     * on the hit points.
     * @param hitter - the ball that hit.
     * @param collisionPoint - the collisionPoint on this paddle.
     * @param currentVelocity - the velocity of the ball.
     * @return - the new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        // divides this paddle into 5 lines (areas).
        double y = this.paddle.getUpperLeft().getY(), tmpWidth = this.width / 5.0;
        Line l1 = new Line(this.paddle.getUpperLeft(),
                new Point(this.paddle.getUpperLeft().getX() + tmpWidth, y));
        Line l2 = new Line(l1.end(), new Point(l1.end().getX() + tmpWidth, y));
        Line l3 = new Line(l2.end(), new Point(l2.end().getX() + tmpWidth, y));
        Line l4 = new Line(l3.end(), new Point(l3.end().getX() + tmpWidth, y));
        Line l5 = new Line(l4.end(), new Point(l4.end().getX() + tmpWidth, y));
        double tmpSpeed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy());

        // checks where the hit occurs and sets the new velocity.
        if (l1.isOnLine(collisionPoint)) {
            return (Velocity.fromAngleAndSpeed(REGION1, tmpSpeed));
        } else if (l2.isOnLine(collisionPoint)) {
            return (Velocity.fromAngleAndSpeed(REGION2, tmpSpeed));
        } else if (l3.isOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * MINUS_ONE);
        } else if (l4.isOnLine(collisionPoint)) {
            return (Velocity.fromAngleAndSpeed(REGION4, tmpSpeed));
        } else if (l5.isOnLine(collisionPoint)) {
            return (Velocity.fromAngleAndSpeed(REGION5, tmpSpeed));
        } else if (this.paddle.getUpperSide().isOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * MINUS_ONE);
        } else if (this.paddle.getLeftSide().isOnLine(collisionPoint)) {
            return (Velocity.fromAngleAndSpeed(REGION1, tmpSpeed));
        } else if (this.paddle.getRightSide().isOnLine(collisionPoint)) {
        return (Velocity.fromAngleAndSpeed(REGION5, tmpSpeed));
        }
        return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * MINUS_ONE);
    }

    /**
     * adds this paddle to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Returns the paddle to the center.
     */
    public void resetPaddle() {
        this.paddle = new Rectangle(new Point(this.paddleX, PADDLE_Y), this.width, HEIGHT);
    }
}