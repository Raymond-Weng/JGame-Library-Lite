package jGame.loop.render;

import jGame.loop.timer.Timer;
import jGame.main.Game;
import jGame.main.ReadyChecker;

public abstract class Render extends Timer {
    protected Game game = null;

    /**
     * create the object and set the rate
     *
     * @param updateRate the rate of updating (for second), the max ups will be (1/updateRate)
     */
    public Render(double updateRate) {
        super(updateRate);
    }


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
