package jGame.output.listener;

import jGame.core.Position;

public interface MouseListener {
    boolean isMouseInside();
    boolean isMousePressed(int button);
    Position getMousePos();
}
