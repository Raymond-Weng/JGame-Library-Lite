package jGame.gameObject.hitboxShape;

import jGame.core.Position;

public class Circle extends Shape {

    private Position position;
    private double radius;

    public Circle(Position position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public boolean intersects(Shape shape) {
        Circle circle = (Circle) shape;
        return (Math.sqrt(Math.pow(this.position.getIntX() - circle.getPosition().getIntX(), 2) +
                Math.pow(this.position.getIntX() - circle.getPosition().getIntX(), 2)) <=
                (this.radius + circle.radius));
    }

    public Position getPosition() {
        return this.position;
    }

    public double getRadius() {
        return this.radius;
    }
}
