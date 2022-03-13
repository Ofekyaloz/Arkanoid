package shapes;

// ID 206666729

/**
 * @author Ofek Yaloz
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * sets the dx and dy values.
     * @param dx - dx value.
     * @param dy - dy value.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point position (x+dx, y+dy).
     * @param p - the point at the beginning.
     * @return - the new point after changing the location.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * sets this shapes.Velocity with the new values of dx and dy.
     * @param dx1 - dx value.
     * @param dy1 - dy value.
     */
    public void setVelocity(double dx1, double dy1) {
        this.dx = dx1;
        this.dy = dy1;
    }

    /**
     * sets this shapes.Velocity to the new shapes.Velocity.
     * @param v2 - the new shapes.Velocity.
     */
    public void setVelocity(Velocity v2) {
        this.dx = v2.getDx();
        this.dy = v2.getDy();
    }

    /**
     * @return - this dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return - this dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Converts the angle and the speed values to dx and dy values.
     * @param angle - angle value in degrees.
     * @param speed - speed value.
     * @return - the new velocity with the new dx and dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
