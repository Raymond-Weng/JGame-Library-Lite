package jGame.debug;

import jGame.main.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * this is made for debugging, set debug to true to enable this panel
 */
public class DebugPanel extends Thread {
    private Game game;

    private JFrame jFrame;
    private JTextArea jTextArea;

    private ArrayList<String> names;
    private Map<String, DebugStringHandler> debugStringHandlerMap;

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
        this.UPDATE_RATE = 1d;
        game.loading = true;
        jFrame = new JFrame();

        jTextArea = new JTextArea();
        jTextArea.setText("Loading...");
        Font font = jTextArea.getFont();
        jTextArea.setFont(new Font(font.getName(), font.getStyle(), 30));

        jFrame.setLayout(new FlowLayout());
        jFrame.getContentPane().add(jTextArea);
        jFrame.pack();
        jFrame.setTitle("Game Debug");
        jFrame.setVisible(true);
    }

    private final double UPDATE_RATE;
    private double lastUpdate;
    private double accumulator = 0;

    @Override
    public void run() {
        while (game.getMainThread().isRunning()) {
            double currentTimeMillis = System.currentTimeMillis();
            accumulator += currentTimeMillis - lastUpdate;
            lastUpdate = currentTimeMillis;
            if (accumulator > UPDATE_RATE * 100d) {
                action();
                accumulator -= UPDATE_RATE * 1000d;
            }
        }
    }

    private void action() {
        AtomicReference<String> text = new AtomicReference<>("");
        names.forEach(name -> text.set(text + name + " : " + debugStringHandlerMap.get(name).getText(game) + "\n"));
        jTextArea.setText(text.get());
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
        this.names.add(name);
        this.debugStringHandlerMap.put(name, debugStringHandler);
    }
}
