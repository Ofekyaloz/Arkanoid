package shapes;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;
import java.util.List;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Line {
    private static final int ZERO = 0, TWO = 2, BOUND1 = 300, BOUND2 = 400;
    private Point p1;
    private Point p2;
    private double length;
    private double incline;


    /**
     * Sets the values of the line: start, end, lenght and incline.
     *
     * @param start - the start point of the line.
     * @param end   - the end point of the line.
     */
    public Line(Point start, Point end) {
        if (start.getX() < end.getX()) {
            this.p1 = start;
            this.p2 = end;
        } else if (start.getX() > end.getX()) {
            this.p2 = start;
            this.p1 = end;
        } else {
            if (start.getY() > end.getY()) {
                this.p1 = end;
                this.p2 = start;
            } else {
                this.p2 = end;
                this.p1 = start;
            }
        }
        this.length = getLenght();
        this.incline = getIncline();
    }

    /**
     * receives 4 arguments and create two points and calls the other constructor.
     *
     * @param x1 - x value of one of the points.
     * @param y1 - y value of one of the points.
     * @param x2 - x value of the second point.
     * @param y2 - y value of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return - the lenght of the line.
     */
    private double getLenght() {
        return this.p1.distance(this.p2);
    }

    /**
     * @return the lenght of the line.
     */
    public double length() {
        return this.length;
    }

    /**
     * Calculate the incline of the line.
     *
     * @return - the incline, if the line parallel to x/y line returns 0.
     */
    private double getIncline() {
        if (this.p1.getX() - this.p2.getX() != ZERO) {
            return (this.p1.getY() - this.p2.getY()) / (this.p1.getX() - this.p2.getX());
        } else {
            return this.p1.getX() - this.p2.getX();
        }
    }

    /**
     * @return - a shapes.Point - the middle of the line.
     */
    public Point middle() {
        double x = (this.p1.getX() + this.p2.getX()) / TWO;
        double y = (this.p1.getY() + this.p2.getY()) / TWO;
        return new Point(x, y);
    }

    /**
     * @return - the start point of the line.
     */
    public Point start() {
        return this.p1;
    }

    /**
     * @return - the end point of the line.
     */
    public Point end() {
        return this.p2;
    }

    /**
     * @param other - the other line.
     * @return - Returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {

        // comparing the lines and the points
        if (this.equals(other) || this.p1.equals(other.p1) || this.p1.equals(other.p2) || this.p2.equals(other.p1)
                || this.p2.equals(other.p2)) {
            return true;
        }
        // comparing the x, y range
        if ((this.start().getX() > other.end().getX() && this.end().getX() > other.start().getX())
                || (this.end().getX() < other.start().getX() && this.start().getX() < other.start().getX())
                || (this.p1.getY() < other.p1.getY() && this.p2.getY() < other.p2.getY()
                && this.p2.getY() < other.p1.getY())) {
            return false;
        }

        // checks lines with incline zero
        if (this.incline == other.incline && this.incline == ZERO) {

            // both of them parallel to y line
            if (this.p1.getX() == this.p2.getX() && other.p1.getX() == other.p2.getX()
                    && (other.p2.getY() < this.p1.getY()) || this.p2.getY() < other.p1.getY()) {
                return false;
            }

            // both of them parallel to x line
            if (this.p1.getY() == this.p2.getY() && other.p1.getY() == other.p2.getY()
                    && (other.p2.getX() < this.p1.getX() || this.p2.getX() < other.p1.getX())) {
                return false;
            }

            // one of them parallel to the x line and the other to the y line
            if (this.p1.getX() == this.p2.getX() && other.p1.getY() == other.p2.getY()
                    && (other.p1.getY() < this.p1.getY() || this.p2.getY() < other.p1.getY())) {
                return false;
            }
            if (this.p1.getY() == this.p2.getY() && other.p1.getX() == other.p2.getX()
                    && (this.p1.getY() < other.p1.getY() || other.p2.getY() < this.p1.getY())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param other - the other line.
     * @return - Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other) || this.equals(other)) {
            return null;
        }

        // checking lines with the same incline
        if (this.incline == other.incline) {

            // comparing points
            if (this.start().equals(other.end())) {
                return this.start();
            } else if (this.end().equals(other.start())) {
                return this.end();
            }

            // one line parallel to x and the second parallel to the y
            if (this.p1.getY() == this.p2.getY() && other.p1.getX() == other.p2.getX()
                    && other.p1.getY() <= this.p1.getY() && this.p1.getY() <= other.p2.getY()) {
                return new Point(other.p1.getX(), this.p1.getY());
            } else if (this.p1.getX() == this.p2.getX() && other.p1.getY() == other.p2.getY()
                    && this.p1.getY() <= other.p1.getY() && other.p1.getY() <= this.p2.getY()) {
                return new Point(this.p1.getX(), other.p1.getY());
            } else {
                return null;
            }
        }
        double b1 = this.p1.getY() - this.incline * this.p1.getX();
        double b2 = other.p1.getY() - other.incline * other.p1.getX();

        // if one of the lines is parallel to x line
        if (this.incline == ZERO && this.p1.getX() == this.p2.getX()) {
            double y = other.incline * this.p1.getX() + b2;
            if (this.p1.getY() <= y && y <= this.p2.getY()) {
                return new Point(this.p1.getX(), y);
            }
        } else if (other.incline == ZERO && other.p1.getX() == other.p2.getX()) {
            double y = this.incline * other.p1.getX() + b1;
            if (other.p1.getY() <= y && y <= other.p2.getY()) {
                return new Point(other.p1.getX(), y);
            }
        }

        // if one of the lines is parallel to y line
        if (this.incline == ZERO && this.p1.getY() == this.p2.getY()) {
            double x = (this.p1.getY() - b2) / other.incline;
            if (this.isXOnLines(other, x)) {
                return new Point(x, this.p1.getY());
            }
        } else if (other.incline == ZERO && other.p1.getY() == other.p2.getY()) {
            double x = (other.p1.getY() - b1) / this.incline;
            if (this.isXOnLines(other, x)) {
                return new Point(x, other.p1.getY());
            }
        }
        double x = (b2 - b1) / (this.incline - other.incline);
        double y = this.incline * x + b1;
        if (this.isXOnLines(other, x)) {
            return new Point(x, y);
        } else {
            return null;
        }
    }

    /**
     * checks if a x value is in the range of the lines.
     *
     * @param other - other other line.
     * @param x     - x value.
     * @return - true is on the lines, false otherwise.
     */
    private boolean isXOnLines(Line other, double x) {
        return (this.p1.getX() <= x && x <= this.p2.getX() && other.p1.getX() <= x && x <= other.p2.getX());
    }

    /**
     * checks if a y value is in the range of the lines.
     * @param y     - y value.
     * @return - true is on the lines, false otherwise.
     */
    private boolean isYOnLines(double y) {
        return (this.p1.getY() <= y && y <= this.p2.getY());
    }

    /**
     * checks if a point is on this line.
     * @param p - the point we check.
     * @return - true the point is on the line else false.
     */
    public boolean isOnLine(Point p) {
        return this.isXOnLines(this, p.getX()) && this.isYOnLines(p.getY());
    }

    /**
     * @param other - the other line.
     * @return - returns true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.length == other.length
                && (this.p1.equals(other.p1) || this.p1.equals(other.p2))
                && (this.p2.equals(other.p1) || this.p2.equals(other.p2)));
    }

    /**
     * generate a random line.
     *
     * @return - a shapes.Line.
     */
    public Line generateRandomLine() {
        Random rand = new Random();
        int x1, x2, y1, y2;
        x1 = rand.nextInt(BOUND2);
        y1 = rand.nextInt(BOUND1);
        x2 = rand.nextInt(BOUND2);
        y2 = rand.nextInt(BOUND1);
        return new Line(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * draws a line on the surface.
     * @param l - line.
     * @param d - the draw surface.
     */
    void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.black);
        d.drawLine((int) l.p1.getX(), (int) l.p1.getY(), (int) l.p2.getX(), (int) l.p2.getY());
    }

    /**
     * checks if this line intersection with the rectangle and returns the closest point if exists.
     * @param rect - the shapes.Rectangle we checks if collisions with the line.
     * @return - the closest intersection point of this line with the rectangle if exists, else null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> l = rect.intersectionPoints(this);
        if (l == null) {
            return null;
        }
        Point p = null;
        for (Point tmpPoint : l) {
            if (tmpPoint != null) {
                if (p == null) {
                    p = tmpPoint;
                } else if ((this.start().distance(tmpPoint) <= this.start().distance(p))) {
                    p = tmpPoint;
                }
            }
        }
        return p;
    }
}