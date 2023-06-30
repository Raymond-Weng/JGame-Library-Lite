package jGame.core;

/**
 * this mark the position in a 2D world
 */

public class Position {
    private final double X;
    private final double Y;

    /**
     * create the object
     *
     * @param x the X of the position
     * @param y the Y of the position
     */
    public Position(double x, double y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * get the X of the position
     *
     * @return X of the position
     */
    public double getX() {
        return X;
    }

    /**
     * get the Y of the position
     *
     * @return Y of the position
     */
    public double getY() {
        return Y;
    }

    /**
     * get the X of the position in int
     *
     * @return X of the position in int
     */
    public int getIntX() {
        return (int) X;
    }

    /**
     * get the Y of the position in int
     *
     * @return Y of the position in int
     */
    public int getIntY() {
        return (int) Y;
    }
}
