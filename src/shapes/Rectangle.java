package shapes;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// ID 206666729
/**
 * @author Ofek Yaloz
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private Line upperSide;
    private Line lowerSide;
    private Line leftSide;
    private Line rightSide;
    private Color color;

    /**
     * creates the rectangle - sets the height, width, points and lines.
     * @param upperLeft - the upper-left point of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
        this.upperSide = new Line(this.upperLeft, this.upperRight);
        this.lowerSide = new Line(this.lowerLeft, this.lowerRight);
        this.leftSide = new Line(this.lowerLeft, this.upperLeft);
        this.rightSide = new Line(this.lowerRight, this.upperRight);
    }

    /**
     * creates a list that contains the intersection point if exists.
     * @param line - a list of the intersection points.
     * @return list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List l = new ArrayList();
        if (this.rightSide.intersectionWith(line) != null) {
            l.add(this.rightSide.intersectionWith(line));
        }
        if (this.leftSide.intersectionWith(line) != null) {
            l.add(this.leftSide.intersectionWith(line));
        }
        if (this.lowerSide.intersectionWith(line) != null) {
            l.add(this.lowerSide.intersectionWith(line));
        }
        if (this.upperSide.intersectionWith(line) != null) {
            l.add(this.upperSide.intersectionWith(line));
        }
        return l;
    }

    /**
     * @return - returns the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return - returns the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return - returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return - returns the upper-side line of the rectangle.
     */
    public Line getUpperSide() {
        return this.upperSide;
    }

    /**
     * @return - returns the lower-side line of the rectangle.
     */
    public Line getLowerSide() {
        return this.lowerSide;
    }

    /**
     * @return - returns the left-side line of the rectangle.
     */
    public Line getLeftSide() {
        return this.leftSide;
    }

    /**
     * @return - returns the right-side line of the rectangle.
     */
    public Line getRightSide() {
        return this.rightSide;
    }

    /**
     * Draws the rectangle on the surface.
     * @param surface - the surface we draw on the rectangle.
     */
    public void drawnOn(DrawSurface surface) {
        if (color == null) {
            color = Color.black;
        }
        surface.setColor(this.color);
        surface.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }

    /**
     * sets the rectangle color.
     * @param c - the color of the rectangle.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Checks if two Rectangles are the same.
     * @param other - the other shapes.Rectangle.
     * @return - true if they are the same, else false.
     */
    public Boolean equal(Rectangle other) {
        if (this.width == other.getWidth() && this.height == other.getHeight()
                && this.upperLeft.equals(other.getUpperLeft())) {
            return true;
        }
        return false;
    }
}