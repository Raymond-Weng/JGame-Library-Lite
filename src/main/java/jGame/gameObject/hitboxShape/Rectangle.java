package jGame.gameObject.hitboxShape;

import jGame.core.Position;
import jGame.core.Size;

public class Rectangle extends Shape {
    private java.awt.Rectangle rectangle;

    /**
     * create a rectangle
     *
     * @param position the position of the top left corner
     * @param size     the size of the rectangle
     */
    public Rectangle(Position position, Size size) {
        this.rectangle = new java.awt.Rectangle(position.getIntX(), position.getIntY(), size.getIntWidth(), size.getIntHeight());
    }

    @Override
    public boolean intersects(Shape shape) {
        Rectangle rectangleArg = (Rectangle) shape;
        return rectangleArg.getRectangle().intersects(this.rectangle);
    }

    /**
     * get the object in class {@code Rectangle} of this
     *
     * @return the rectangle object
     */
    public java.awt.Rectangle getRectangle() {
        return this.rectangle;
    }
}
