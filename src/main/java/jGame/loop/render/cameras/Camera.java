package jGame.loop.render.cameras;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;

/**
 * this is a class for camera controlling
 *
 * PLEASE handle the stat "CAMERA_READY"
 */
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

    /**
     * give the render the display area, and let it handle the zooming (not the size of the frame)
     * @return the size to display
     */
    Size getDisplayArea();
}
