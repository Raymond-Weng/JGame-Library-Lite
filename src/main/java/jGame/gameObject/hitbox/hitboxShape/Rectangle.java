package jGame.gameObject.hitbox.hitboxShape;

import jGame.core.Position;
import jGame.core.Size;

public class Rectangle extends Shape {
    private final java.awt.Rectangle rectangle;

    /**
     * create a rectangle
     *
     * @param position the position of the top left corner
     * @param size     the size of the rectangle
     */
    public Rectangle(Position position, Size size) {
        this.rectangle = new java.awt.Rectangle(position.getIntX() - (size.getIntWidth() / 2), position.getIntY() - (size.getIntHeight() / 2), size.getIntWidth(), size.getIntHeight());
    }

    public Rectangle(Position position1, Position position2){
        this(new Position((position1.getX() + position2.getX()) / 2, (position1.getY() + position2.getY()) / 2),
                new Size(Math.abs(position1.getX() - position2.getX()), Math.abs(position1.getY() - position2.getY())));
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
