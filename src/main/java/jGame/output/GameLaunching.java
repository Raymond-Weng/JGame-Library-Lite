package jGame.output;

import jGame.main.Game;

import java.awt.*;

/**
 * the loading page for waiting everything ready
 */
public class GameLaunching extends Thread {
    private Game game;
    private final double UPDATE_RATE;
    private double lastUpdate;
    private double accumulator = 0;

    /**
     * create a loading page
     * @param game
     */
    public GameLaunching(Game game) {
        super();
        this.game = game;
        this.UPDATE_RATE = 1d;
        game.loading = true;
    }

    @Override
    public void run() {
        while (game.loading) {
            double currentTimeMillis = System.currentTimeMillis();
            accumulator += currentTimeMillis - lastUpdate;
            lastUpdate = currentTimeMillis;
            if (accumulator > UPDATE_RATE * 1000d) {
                action();
                accumulator -= UPDATE_RATE * 1000d;
            }
        }
    }

    private void action() {
        String text = "Game Launching...";
        Output output = game.getOutput();
        Graphics graphics = output.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, output.getSize().getIntWidth(), output.getSize().getIntHeight());
        graphics.setColor(Color.white);
        int strW = graphics.getFontMetrics().stringWidth(text);
        int stwH = graphics.getFontMetrics().getFont().getSize();
        graphics.drawString(text, (output.getSize().getIntWidth() - strW) / 2, (output.getSize().getIntHeight() - stwH) / 2);
        graphics.dispose();
        output.show();
    }
}
