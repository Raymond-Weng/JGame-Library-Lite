package jGame.gameObject;

import com.sun.istack.internal.NotNull;
import jGame.core.Position;
import jGame.core.Size;

import java.awt.*;

/**
 * the object to check if two things hit together.
 */

public class Hitbox {
    private volatile Rectangle rectangle;

    private boolean isNull = false;

    /**
     * create a hitbox
     *
     * @param position the position of the hitbox
     * @param size     the size of the hitbox
     */
    public Hitbox(Position position, Size size) {
        rectangle = new Rectangle(position.getIntX(), position.getIntY(), size.getIntWidth(), size.getIntHeight());
    }

    /**
     * create a hitbox that won't hit any others
     */
    public Hitbox() {
        isNull = true;
    }

    /**
     * check if two hitbox hit together
     *
     * @param hitbox the other hitbox
     * @return if the two hitbox hit together
     */
    public boolean isHit(@NotNull Hitbox hitbox) {
        if (hitbox.isNull || this.isNull)
            return false;
        else
            return hitbox.getRectangle().intersects(this.rectangle);
    }

    /**
     * get the Rectangle object of the hitbox
     *
     * @return the Rectangle object of the hitbox
     */
    public Rectangle getRectangle() {
        return rectangle;
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
     * set the position of the hitbox
     *
     * @param position the new position of the hitbox
     */
    public void setPosition(@NotNull Position position) {
        this.rectangle = new Rectangle(position.getIntX(), position.getIntY(), this.rectangle.width, this.rectangle.height);
    }

    /**
     * set the size of the new hitbox
     *
     * @param size the new size of the hitbox
     */
    public void setSize(@NotNull Size size) {
        this.rectangle = new Rectangle(this.rectangle.x, this.rectangle.y, size.getIntWidth(), size.getIntHeight());
    }
}
