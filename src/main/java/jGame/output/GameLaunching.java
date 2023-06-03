package jGame.output;

import jGame.Game;

import java.awt.*;

public class GameLaunching extends Thread {
    private Game game;

    private final double updateRate;
    private double lastUpdate;
    private double accumulator = 0;

    public GameLaunching(Game game) {
        super();
        this.game = game;
        this.updateRate = 1d;
    }

    @Override
    public void run() {
        game.loading = true;
        while (game.loading) {
            double currentTimeMillis = System.currentTimeMillis();
            accumulator += currentTimeMillis - lastUpdate;
            lastUpdate = currentTimeMillis;
            if (accumulator > updateRate * 1000d) {
                action();
                accumulator -= updateRate * 1000d;
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
