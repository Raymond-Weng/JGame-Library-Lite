package jGame.output;

import jGame.main.Game;

import java.awt.*;
import java.util.concurrent.locks.LockSupport;

/**
 * the loading page for waiting everything ready
 */
public class GameLaunching extends Thread {
    private final Game game;
    private final double UPDATE_RATE;

    /**
     * how often to check if the loading is finished
     */
    private static final long CHECK_RATE_NANOS = 10_000_000L;

    /**
     * create a loading page
     *
     * @param game the current game
     */
    public GameLaunching(Game game) {
        super();
        this.game = game;
        this.UPDATE_RATE = 1d;
        game.loading = true;
    }

    @Override
    public void run() {
        long updateRateNanos = (long) (UPDATE_RATE * 1_000_000_000d);
        long nextUpdate = System.nanoTime();
        while (game.loading) {
            if (System.nanoTime() - nextUpdate >= 0) {
                action();
                nextUpdate += updateRateNanos;
            }
            LockSupport.parkNanos(Math.min(CHECK_RATE_NANOS, nextUpdate - System.nanoTime()));
        }
    }

    private void action() {
        String text = "Game Launching...";
        Output output = game.getOutput();
        Graphics graphics = output.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, output.getSize().getIntWidth(), output.getSize().getIntHeight());
        graphics.setColor(Color.white);
        graphics.setFont(new Font(null, Font.PLAIN, game.FONT_SIZE));
        int strW = graphics.getFontMetrics().stringWidth(text);
        int stwH = graphics.getFontMetrics().getFont().getSize();
        graphics.drawString(text, (output.getSize().getIntWidth() - strW) / 2, (output.getSize().getIntHeight() - stwH) / 2);
        graphics.dispose();
        output.show();
    }
}
