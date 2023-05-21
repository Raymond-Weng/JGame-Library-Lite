/**
 * For talk about size of object or frame etc.
 */
package jGame.core;

public class Size {
    private double width;
    private double height;

    /**
     * Creates object.
     *
     * @param width  the width of the object
     * @param height the height of the object
     */
    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * gets the width
     *
     * @return width of the object
     */
    public double getWidth() {
        return width;
    }

    /**
     * get the height
     *
     * @return height of the object
     */
    public double getHeight() {
        return height;
    }
}
