package jGame.loop.render.cameras;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.input.MouseListenerImpl;

import java.awt.event.MouseEvent;

public class ScrollableCamera implements Camera {
    private final MouseListenerImpl mouseListener;
    private final Size displaySize;
    private Position position = new Position(0, 0);
    private Position lastPosition;

    public ScrollableCamera(MouseListenerImpl mouseListener, Size displaySize) {
        this.mouseListener = mouseListener;
        lastPosition = mouseListener.getMousePos();
        this.displaySize = displaySize;
    }

    @Override
    public void update() {
        Position nowPosition = mouseListener.getMousePos();
        if (mouseListener.isMousePressed(MouseEvent.BUTTON1) && nowPosition.equals(lastPosition)) {
            position = position.add(lastPosition.subtract(nowPosition));
        }
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
        return position;
    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public Size getDisplayArea() {
        return displaySize;
    }
}
