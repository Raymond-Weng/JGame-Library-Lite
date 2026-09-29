package jGame.debug;

import jGame.main.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

/**
 * this is made for debugging, set debug to true to enable this panel
 */
public class DebugPanel extends Thread {
    private final Game game;
    private final JFrame jFrame;
    private final JTextArea jTextArea;
    private final ArrayList<String> names;
    private final Map<String, DebugStringHandler> debugStringHandlerMap;

    /**
     * this method will be called if the debug is set to true in Game.
     *
     * @param game the current game of this panel
     */
    public DebugPanel(Game game) {
        super();

        names = new ArrayList<>();
        debugStringHandlerMap = new HashMap<>();

        this.game = game;
        this.UPDATE_RATE = 0.1d;
        jFrame = new JFrame();

        jTextArea = new JTextArea();
        jTextArea.setText("Loading...");
        Font font = jTextArea.getFont();
        jTextArea.setFont(new Font(font.getName(), font.getStyle(), game.FONT_SIZE));

        jFrame.setFocusable(false);
        jTextArea.setFocusable(false);

        jFrame.setLayout(new FlowLayout());
        jFrame.getContentPane().add(jTextArea);
        jFrame.pack();
        jFrame.setTitle("Game Debug");
        jFrame.setVisible(true);
    }

    private final double UPDATE_RATE;

    @Override
    public void run() {
        long updateRateNanos = (long) (UPDATE_RATE * 1_000_000_000d);
        while (game.getMainThread().isRunning()) {
            action();
            LockSupport.parkNanos(updateRateNanos);
        }
    }

    private void action() {
        StringBuilder text = new StringBuilder();
        synchronized (names) {
            names.forEach(name -> text.append(name).append(" : ").append(debugStringHandlerMap.get(name).getText(game)).append("\n"));
        }
        jTextArea.setText(text.toString());
        jFrame.pack();
    }

    /**
     * add something to the panel, and it will start tracking it, and it will show as 'name + " : " + the string returned' .
     *
     * @param name               the name of the tracker
     * @param debugStringHandler the handler to return the string to this panel
     * @see DebugStringHandler
     */
    public void addVariable(String name, DebugStringHandler debugStringHandler) {
        synchronized (names) {
            if (!this.debugStringHandlerMap.containsKey(name)) {
                this.names.add(name);
            }
            this.debugStringHandlerMap.put(name, debugStringHandler);
        }
    }
}
