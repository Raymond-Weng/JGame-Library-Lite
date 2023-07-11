package jGame.loop.update;

import com.sun.istack.internal.NotNull;
import jGame.loop.timer.Timer;
import jGame.main.Game;
import jGame.main.ReadyChecker;

/**
 * the update, computing control of the game
 */
public abstract class Update extends Timer {
    protected Game game = null;

    /**
     * create a update object
     * @param updateRateSecond the update rate of this
     */
    public Update(double updateRateSecond) {
        super(updateRateSecond);
    }

    /**
     * give the update object a game to let it knows more
     * @param game the current game
     */
    public void setGame(@NotNull Game game) {
        this.game = game;
        ReadyChecker.setStatBoolean(ReadyChecker.UPDATE_READY, true);
    }

    @Override
    public void action(){
        updateGame();
        if(game.ONLY_RENDER_AFTER_UPDATE){
            game.getMainThread().getTimerManager().getRender().renderGame();
        }
    }

    /**
     * update once (compute position, size...)
     */
    public abstract void updateGame();
}
