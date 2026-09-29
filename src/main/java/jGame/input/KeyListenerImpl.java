package jGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * the better key input, including saving the event from frame
 */
public class KeyListenerImpl implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        setKey(e.getKeyCode(), true);
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        setKey(e.getKeyCode(), false);
    }

    private void setKey(int keyCode, boolean pressed) {
        while (keyPressed.size() <= keyCode) {
            keyPressed.add(false);
        }
        keyPressed.set(keyCode, pressed);
    }

    private final ArrayList<Boolean> keyPressed;

    /**
     * create a new key input
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
    public synchronized boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keyPressed.size()) {
            return false;
        }
        return keyPressed.get(keyCode);
    }
}
