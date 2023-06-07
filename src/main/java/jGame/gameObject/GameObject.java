package jGame.gameObject;

import jGame.core.Position;
import jGame.core.Size;

import java.awt.*;

/**
 * the main actor of the game, object, acts as every part shown on the screen.
 */

public abstract class GameObject {

    /**
     * update the size, position, value to make change in the game.
     */
    public abstract void update();

    /**
     * give the game thread the image of the object, to let game show the object on the screen
     *
     * @return the image of the object
     */
    public abstract Image render();

    /**
     * return the position of the object to the game to let it know where to draw the object
     *
     * @return the position of the object
     */
    public abstract Position getPosition();

    /**
     * make others get the hitbox of the object to know if it hit the object
     *
     * @return the hitbox of the object
     */
    public abstract Hitbox getHitbox();

    /**
     * return the size of the object
     * @return the size of the object
     */
    public abstract Size getSize();

}
