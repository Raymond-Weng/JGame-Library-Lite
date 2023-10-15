package jGame.input;

import jGame.core.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * the better mouse input, including saving the event from frame
 */
public class MouseListenerImpl implements java.awt.event.MouseListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseInside = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInside = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos = new Position(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = new Position(e.getX(), e.getY());
    }

    private volatile boolean mouseInside;
    private final boolean[] mousePressed = new boolean[MouseInfo.getNumberOfButtons() + 1];
    private volatile Position mousePos = new Position(0, 0);

    /**
     * check if the mouse is inside the frame
     *
     * @return is the mouse inside the frame
     */
    public boolean isMouseInside() {
        return mouseInside;
    }

    /**
     * check if the mouse is pressed
     *
     * @param button the button code defined in {@code KeyEvent}
     * @return is the mouse button pressed
     */
    public boolean isMousePressed(int button) {
        return mousePressed[button];
    }

    /**
     * get the position of the mouse
     *
     * @return the position of the mouse
     */
    public Position getMousePos() {
        return mousePos;
    }
}
