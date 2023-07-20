package jGame.loop.render;

import jGame.loop.timer.Timer;
import jGame.main.Game;
import jGame.main.ReadyChecker;
import jGame.output.Output;

import java.awt.*;

/**
 * the render, graphic control of the game
 */
public abstract class Render extends Timer {
    protected volatile Game game = null;

    /**
     * create the object and set the rate (the rate won't matter if the boolean {@code OnlyRenderAfterUpdate is true})
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
        if(game.second_loading){
            Output output = game.getOutput();
            Graphics graphics = output.getGraphics();
            graphics.drawImage(game.secondLoading.render(),
                    0,
                    0,
                    null);
            graphics.dispose();
            output.show();
        }
        if (!game.ONLY_RENDER_AFTER_UPDATE) {
            renderGame();
        }
        this.updateTime--;
    }

    /**
     * render one frame
     *
     * PLEASE handle the {@code updateTime} or the {@code fps} won't work
     */
    public abstract void renderGame();
}
