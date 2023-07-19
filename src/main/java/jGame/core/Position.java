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

    /**
     * get the distance between this and another point
     * @param position the second position
     * @return the distance between this and the second position
     */
    public double distance(Position position) {
        return Math.sqrt(Math.pow(this.getX() - position.getX(), 2) + Math.pow(this.getY() + position.getY(), 2));
    }

    /**
     * Adds a specified vector to this instance's elements.
     *
     * @param vec addend
     * @return quotient.
     */
    public Position add(Vector vec) {
        return new Position(this.X + vec.getX(), this.Y + vec.getY());
    }

    /**
     * Adds a specified position and vector together.
     *
     * @param pos summand.
     * @param vec addend.
     * @return quotient.
     */
    public Position add(Position pos, Vector vec) {
        return new Position(pos.getX() + vec.getX(), pos.getY() + vec.getY());
    }

    /**
     * Adds a specified position and vector together.
     *
     * @param vec summand.
     * @param pos addend.
     * @return quotient.
     */
    public Position add(Vector vec, Position pos) {
        return new Position(pos.getX() + vec.getX(), pos.getY() + vec.getY());
    }

    /**
     * Subtracts this instance by a vector.
     *
     * @param vec subtrahend.
     * @return quotient.
     */
    public Position subtract(Vector vec) {
        return new Position(this.X - vec.getX(), this.Y - vec.getY());
    }

    /**
     * Subtracts a specified position by a specified vector.
     *
     * @param pos minuend.
     * @param vec subtrahend.
     * @return quotient.
     */

    public Position subtract(Position pos, Vector vec) {
        return new Position(pos.getX() - vec.getX(), pos.getY() - vec.getY());
    }
}