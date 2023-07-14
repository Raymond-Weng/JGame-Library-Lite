package jGame.gameObject.hitboxShape;

import jGame.core.Position;

public class Circle extends Shape {

    private final Position position;
    private final double radius;

    /**
     * create a new circle
     *
     * @param position the position of the center
     * @param radius   the radius in pixel
     */
    public Circle(Position position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public boolean intersects(Shape shape) {
        Circle circle = (Circle) shape;
        return (Math.sqrt(Math.pow(this.position.getIntX() - circle.getPosition().getIntX(), 2) +
                Math.pow(this.position.getIntX() - circle.getPosition().getIntX(), 2)) <=
                (this.radius + circle.getRadius()));
    }

    /**
     * get the position of the center
     *
     * @return the position of the center
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * get the radius of the circle
     *
     * @return the radius of the circle
     */
    public double getRadius() {
        return this.radius;
    }
}
