package jGame.gameObject.hitbox;

import java.util.ArrayList;

/**
 * the object to check if two things hit together.
 */

public class Hitbox<E extends jGame.gameObject.hitbox.hitboxShape.Shape> {
    private volatile E shape;

    private final boolean isNull;

    private final ArrayList<Hitbox<E>> arrayList;

    /**
     * create a hittable hitbox
     *
     * @param shape the shape of the hitbox
     * @see jGame.gameObject.hitbox.hitboxShape.Shape
     */
    public Hitbox(E shape) {
        this.shape = shape;
        this.isNull = false;
        this.arrayList = new ArrayList<>();
        this.arrayList.add(this);
    }

    /**
     * create a hitbox that won't hit any others
     */
    public Hitbox() {
        isNull = true;
        this.arrayList = new ArrayList<>();
        this.arrayList.add(this);
    }

    /**
     * check if two hitbox hit together
     *
     * @param hitbox the other hitbox
     * @return if the two hitbox hit together
     */
    public boolean isHit(Hitbox<E> hitbox) {
        if (hitbox.isNull || this.isNull)
            return false;
        else
            return shape.intersects(hitbox.getShape());
    }

    public E getShape() {
        return shape;
    }

    /**
     * check if the hitbox cannot hit by other
     *
     * @return is the hitbox cannot hit by other
     */
    public boolean cannotHit() {
        return isNull;
    }

    /**
     * set the shape of the hitbox
     *
     * @param shape the new shape if the hitbox
     */
    public void setShape(E shape) {
        this.shape = shape;
    }

    public ArrayList<Hitbox<E>> getHitboxes() {
        return this.arrayList;
    }
}
