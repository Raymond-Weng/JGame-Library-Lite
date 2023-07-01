package jGame.output.listener;

import jGame.core.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListenerImpl implements java.awt.event.MouseListener, MouseMotionListener, MouseListener {
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
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = new Position(e.getX(), e.getY());
    }

    private boolean mouseInside;
    private boolean[] mousePressed = new boolean[MouseInfo.getNumberOfButtons() + 1];
    private Position mousePos = new Position(0, 0);

    public boolean isMouseInside() {
        return mouseInside;
    }

    public boolean isMousePressed(int button) {
        return mousePressed[button];
    }

    public Position getMousePos() {
        return mousePos;
    }
}
