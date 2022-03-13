package shapes;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Point {
    private double x;
    private double y;

    /**
     * @param x - x value of the point.
     * @param y - y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - return the distance of this point to the other point.
     * @param other - the other point.
     * @return - the distance between this point to the other point.
     */
    public double distance(Point other) {
        double tmpX = other.getX(), tmpY = other.getY();
        return Math.sqrt(((this.x - tmpX) * (this.x - tmpX)) + ((this.y - tmpY) * (this.y - tmpY)));
    }

    /**
     * @param other - the other point.
     * @return - return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(10, -2);
        if (Math.abs(other.getX() - this.getX()) < epsilon && Math.abs(other.getY() - this.getY()) < epsilon) {
            return true;
        }
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * @return - x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return - y value of this point.
     */
    public double getY() {
        return this.y;
    }
}
