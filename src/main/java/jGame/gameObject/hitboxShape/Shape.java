package jGame.gameObject.hitboxShape;

import com.sun.istack.internal.NotNull;

/**
 * the shape of the hitbox, for computing if the two shape hit
 *
 * @see Rectangle
 * @see Circle
 */
public abstract class Shape {
    /**
     * compute if two shape hit together
     *
     * @param shape the second shape
     * @return is two shape hit together
     */
    public abstract boolean intersects(@NotNull Shape shape);
}
