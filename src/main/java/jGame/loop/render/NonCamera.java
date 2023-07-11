package jGame.loop.render;

import com.sun.istack.internal.NotNull;

import jGame.core.Position;
import jGame.gameObject.GameObject;

/**
 * if you don't want to use the camera, you can just use this and the camera will not work at all
 */
public class NonCamera implements Camera {
    @Override
    public void update() {
    }

    @Override
    public void focusOn(GameObject gameObject) {
    }

    @Override
    public GameObject getFocus() {
        return null;
    }

    @Override
    public void clearFocus() {
    }

    @Override
    public Position getPosition() {
        return new Position(0d, 0d);
    }

    @Override
    public void setPosition(@NotNull Position position) {
    }
}
