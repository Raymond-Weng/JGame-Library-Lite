package jGame3D.core;

import jGame.core.Size;

public class Size3D {
    private final double width;
    private final double height;
    private final double deep;

    public Size3D(double width, double height, double deep){
        this.width = width;
        this.height = height;
        this.deep = deep;
    }

    /**
     * Get a new size object with new {@code width}.
     *
     * @param width the new width
     * @return the size object with old height and new width
     */
    public Size3D setWidth(double width) {
        return new Size3D(width, this.height, this.deep);
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
     * gets the width in int
     *
     * @return width of the object in int
     */
    public int getIntWidth() {
        return (int) width;
    }

    /**
     * Get a new size object with new {@code height}.
     *
     * @param height the new height
     * @return the size object with old width and new height
     */
    public Size3D setHeight(double height) {
        return new Size3D(this.width, height, this.deep);
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
     * get the height in int
     *
     * @return height of the object in int
     */
    public int getIntHeight() {
        return (int) height;
    }

    /**
     * Get a new size object with new {@code height}.
     *
     * @param deep the new deep
     * @return the size object with old width and new height
     */
    public Size3D setDeep(double deep) {
        return new Size3D(this.width, this.height, deep);
    }

    /**
     * get the deep
     *
     * @return deep of the object
     */
    public double getDeep() {
        return deep;
    }

    /**
     * get the deep in int
     *
     * @return deep of the object in int
     */
    public int getIntDeep() {
        return (int) deep;
    }
}
