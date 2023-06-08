package jGame.loop.render;

import jGame.core.Position;
import jGame.gameObject.GameObject;

public interface Camera {
    /**
     * update all the things of the camera here, include position, focus etc.
     */
    void update();

    /**
     * focus on something, make the camera follow the object
     *
     * @param gameObject the object to focus on
     */
    void focusOn(GameObject gameObject);

    /**
     * return the object the camera is focusing on
     *
     * @return the object the camera is focusing on
     */
    GameObject getFocus();

    /**
     * cancel the focus
     */
    void clearFocus();

    /**
     * tell user the position of the camera
     *
     * @return the position of the camera
     */
    Position getPosition();

    /**
     * move the camera to somewhere, and this should not work when the camera are following something
     *
     * @param position the new position of the camera
     */
    void setPosition(Position position);
}
