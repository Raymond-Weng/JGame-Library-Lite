package jGame.core;

import java.awt.*;

/**
 * For talk about size of object or frame etc.
 */

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
     * Get a new size object with new {@code width}.
     *
     * @param width the new width
     * @return the size object with old height and new width
     */
    public Size setWidth(double width) {
        return new Size(width, this.height);
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
     * Get a new size object with new {@code height}.
     *
     * @param height the new height
     * @return the size object with old width and new height
     */
    public Size setHeight(double height) {
        return new Size(this.width, height);
    }

    /**
     * get the height
     *
     * @return height of the object
     */
    public double getHeight() {
        return height;
    }

    /**
     * gets the width in int
     *
     * @return width of the object in int
     */
    public int getIntWidth() {
        return (int) width;
    }

    /**
     * get the height in int
     *
     * @return height of the object in int
     */
    public int getIntHeight() {
        return (int) height;
    }

    /**
     * Divides this instance by a specified scalar value.
     *
     * @param val divisor.
     * @return quotient.
     */
    public Size divide(double val) {
        return new Size(width / val, height / val);
    }

    /**
     * Divides the specified size by a specified scalar value.
     *
     * @param size dividend.
     * @param val divisor.
     * @return quotient.
     */
    public static Size divide(Size size, double val) {
        return new Size(size.getWidth() / val, size.getHeight() / val);
    }

    /**
     * Divides this instance by a specified size.
     *
     * @param size dividend.
     * @return quotient.
     */
    public Size divide(Size size) {
        return new Size(width / size.getWidth(), height / size.getHeight());
    }

    /**
     * Divides the first size by the second one.
     *
     * @param size1 dividend.
     * @param size2 divisor.
     * @return quotient.
     */
    public static Size divide(Size size1, Size size2) {
        return new Size(size1.getWidth() / size2.getWidth(), size1.getWidth() / size2.getHeight());
    }

    public Dimension toDimension(){
        return new Dimension((int) width, (int) height);
    }
}
