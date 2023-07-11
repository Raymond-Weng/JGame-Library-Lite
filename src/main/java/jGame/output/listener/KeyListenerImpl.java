package jGame.output.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * the better key listener, including saving the event from frame
 */
public class KeyListenerImpl implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }

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

    /**
     * create a new key listener
     */
    public KeyListenerImpl() {
        keyPressed = new ArrayList<>();
    }

    /**
     * check if the key is pressed
     *
     * @param keyCode the keycode defined in {@code KeyEvent}
     * @return is the key pressed
     * @see KeyEvent
     */
    public boolean isKeyPressed(int keyCode) {
        if (keyPressed.size() <= keyCode) {
            while (keyPressed.size() <= keyCode) {
                keyPressed.add(false);
            }
        }
        return keyPressed.get(keyCode);
    }
}
