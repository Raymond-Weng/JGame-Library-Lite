package jGame.core;

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

}
