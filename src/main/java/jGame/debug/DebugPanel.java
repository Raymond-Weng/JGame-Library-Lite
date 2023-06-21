package jGame.debug;

import jGame.Game;
import jGame.output.Output;

import javax.swing.*;
import java.awt.*;

public class DebugPanel extends Thread {
    private Game game;

    private JFrame jFrame;
    private JLabel jLabel;

    public DebugPanel(Game game) {
        super();
        this.game = game;
        this.UPDATE_RATE = 1d;
        game.loading = true;
        jFrame = new JFrame();
        jLabel = new JLabel();

        jFrame.add(jLabel);
        jFrame.setTitle("Game Debug");
        jFrame.setVisible(true);
    }

    private final double UPDATE_RATE;
    private double lastUpdate;
    private double accumulator = 0;

    @Override
    public void run() {
        while (game.loading) {
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
        this.jLabel.setText("FPS: " + game.getMainThread().getTimerManager().getRender().getUps()
        + "\nUPS: " + game.getMainThread().getTimerManager().getUpdate().getUps());
        jFrame.pack();
    }
}
