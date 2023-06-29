package jGame.output.listener;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {}

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

    private boolean mouseInside;
    private boolean[] mousePressed = new boolean[MouseInfo.getNumberOfButtons() + 1];

    public boolean isMouseInside(){
        return mouseInside;
    }

    public boolean isMousePressed(int button){
        return mousePressed[button];
    }
}
