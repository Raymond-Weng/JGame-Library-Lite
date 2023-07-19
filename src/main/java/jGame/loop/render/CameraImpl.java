package jGame.loop.render;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.main.Game;
import jGame.main.ReadyChecker;

/**
 * make the Camera
 *
 * @see Camera
 */

public class CameraImpl implements Camera {
    private volatile Game game;
    private volatile Position position;
    private volatile Size displaySize;
    private volatile GameObject objectOnFocus;

    /**
     * create a camera
     *
     * @param position    the default position of the camera
     * @param displaySize the render the display area, and let it handle the zooming (not the size of the frame)
     */
    public CameraImpl(Position position, Size displaySize) {
        this(position, displaySize, null);
    }


    /**
     * create a camera
     *
     * @param position      the default position of the camera
     * @param displaySize   the render the display area, and let it handle the zooming (not the size of the frame)
     * @param objectOnFocus let the camera follow an object
     */
    public CameraImpl(Position position, Size displaySize, GameObject objectOnFocus) {
        this.position = position;
        this.displaySize = displaySize;
        this.objectOnFocus = objectOnFocus;
    }

    /**
     * give the camera a current game
     *
     * @param game the game object, we need this to get the size of output
     */
    public void setGame(Game game) {
        this.game = game;
        ReadyChecker.setStatBoolean(ReadyChecker.CAMERA_READY, true);
    }

    @Override
    public void update() {
        if (objectOnFocus != null) {
            this.position = new Position(objectOnFocus.getPosition().getX() - (game.getOutput().getSize().getWidth() - objectOnFocus.getSize().getWidth()) / 2,
                    objectOnFocus.getPosition().getY() - (game.getOutput().getSize().getHeight() - objectOnFocus.getSize().getHeight()) / 2);
        }
    }

    @Override
    public void focusOn(GameObject gameObject) {
        this.objectOnFocus = gameObject;
    }

    @Override
    public GameObject getFocus() {
        return this.objectOnFocus;
    }

    @Override
    public void clearFocus() {
        this.objectOnFocus = null;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Size getDisplayArea() {
        return displaySize;
    }

    public void setDisplaySize(Size displaySize) {
        this.displaySize = displaySize;
    }
}
