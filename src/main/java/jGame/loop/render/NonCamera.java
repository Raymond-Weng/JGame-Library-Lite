package jGame.loop.render;

import jGame.core.Position;
import jGame.gameObject.GameObject;

public class NonCamera implements Camera{
    @Override
    public void update() {}

    @Override
    public void focusOn(GameObject gameObject) {}

    @Override
    public GameObject getFocus() {
        return null;
    }

    @Override
    public void clearFocus() {}

    @Override
    public Position getPosition() {
        return new Position(0d, 0d);
    }

    @Override
    public void setPosition(Position position) {}
}
