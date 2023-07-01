package jGame.output.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyListenerImpl implements KeyListener, jGame.output.listener.KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyPressed.size() <= e.getKeyCode()) {
            while (keyPressed.size() <= e.getKeyCode()) {
                keyPressed.add(false);
            }
        }
        keyPressed.set(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyPressed.size() <= e.getKeyCode()) {
            while (keyPressed.size() <= e.getKeyCode()) {
                keyPressed.add(false);
            }
        }
        keyPressed.set(e.getKeyCode(), false);
    }

    private ArrayList<Boolean> keyPressed;

    public KeyListenerImpl() {
        keyPressed = new ArrayList<>();
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyPressed.size() <= keyCode) {
            while (keyPressed.size() <= keyCode) {
                keyPressed.add(false);
            }
        }
        return keyPressed.get(keyCode);
    }
}
