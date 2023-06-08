package jGame.loop.render;

import jGame.Game;
import jGame.core.Position;
import jGame.gameObject.GameObject;

/**
 * make the Camera
 *
 * @see Camera
 */

public class CameraImpl implements Camera {
    private Game game;
    private Position position;
    private GameObject objectOnFocus;

    /**
     * create a camera
     * @param game the game object, we need this to get the size of output
     * @param position the default position of the camera
     */
    public CameraImpl(Game game, Position position) {
        this(game, position, null);
    }


    /**
     * create a camera
     * @param game the game object, we need this to get the size of output
     * @param position the default position of the camera
     * @param objectOnFocus let the camera follow an object
     */
    public CameraImpl(Game game, Position position, GameObject objectOnFocus) {
        this.game = game;
        this.position = position;
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
}
