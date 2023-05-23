package jGame.core;

public class Position {
    private double x;
    private double y;

    /**
     * create the object
     * @param x the x of the position
     * @param y the y of the position
     */
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * get the x of the position
     * @return x of the position
     */
    public double getX() {
        return x;
    }

    /**
     * get the y of the position
     * @return y of the position
     */
    public double getY() {
        return y;
    }

    /**
     * get the x of the position in int
     * @return x of the position in int
     */
    public int getIntX() {
        return (int)x;
    }

    /**
     * get the y of the position in int
     * @return y of the position in int
     */
    public int getIntY() {
        return (int)y;
    }
}
