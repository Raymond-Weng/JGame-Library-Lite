package jGame.loop.render;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.main.Game;

/**
 * make the Camera
 *
 * @see Camera
 */

public class CameraImpl implements Camera {
    private final Game game;
    private volatile Position position;
    private volatile Size displaySize;
    private volatile GameObject objectOnFocus;

    /**
     * create a camera
     *
     * @param game     the game object, we need this to get the size of output
     * @param position the default position of the camera
     */
    public CameraImpl(Game game, Position position, Size size) {
        this(game, position, size, null);
    }


    /**
     * create a camera
     *
     * @param game          the game object, we need this to get the size of output
     * @param position      the default position of the camera
     * @param objectOnFocus let the camera follow an object
     */
    public CameraImpl(Game game, Position position, Size displaySize, GameObject objectOnFocus) {
        this.game = game;
        this.position = position;
        this.displaySize = displaySize;
        this.objectOnFocus = objectOnFocus;
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

    public void setDisplaySize(Size displaySize){
        this.displaySize = displaySize;
    }
}
