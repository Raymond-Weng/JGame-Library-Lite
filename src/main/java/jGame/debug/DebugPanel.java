package jGame.debug;

import jGame.Game;

import javax.swing.*;
import java.awt.*;

public class DebugPanel extends Thread {
    private Game game;

    private JFrame jFrame;
    private JTextArea jTextArea;

    public DebugPanel(Game game) {
        super();
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
        this.jTextArea.setText("FPS: " + game.getMainThread().getTimerManager().getRender().getUps()
        + "\nUPS: " + game.getMainThread().getTimerManager().getUpdate().getUps());
        jFrame.pack();
    }
}
