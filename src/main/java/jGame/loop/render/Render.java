package jGame.loop.render;

import jGame.loop.timer.Timer;
import jGame.main.Game;
import jGame.main.ReadyChecker;

/**
 * the render, graphic control of the game
 */
public abstract class Render extends Timer {
    protected volatile Game game = null;

    /**
     * create the object and set the rate
     *
     * @param updateRate the rate of updating (for second), the max ups will be (1/updateRate)
     */
    public Render(double updateRate) {
        super(updateRate);
    }

    /**
     * give the game to this to let it know more about this, loading page will run until these "setGame" was called
     *
     * @param game the current game
     */
    public void setGame(Game game) {
        this.game = game;
        ReadyChecker.setStatBoolean(ReadyChecker.RENDER_READY, true);
    }

    @Override
    public void action() {
        if (!game.ONLY_RENDER_AFTER_UPDATE) {
            renderGame();
        }
    }

    /**
     * render one frame
     */
    public abstract void renderGame();
}
