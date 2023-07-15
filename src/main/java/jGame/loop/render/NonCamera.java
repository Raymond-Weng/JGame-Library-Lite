package jGame.loop.render;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;

/**
 * if you don't want to use the camera, you can just use this and the camera will not work at all
 */
public class NonCamera implements Camera {
    private final Size displaySize;

    public NonCamera(Size displaySize){
        this.displaySize = displaySize;
    }

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
    public void setPosition(Position position) {
    }

    @Override
    public Size getDisplayArea(){
        return displaySize;
    }
}
