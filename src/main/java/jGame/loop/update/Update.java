package jGame.loop.update;

import jGame.loop.timer.Timer;
import jGame.main.Game;
import jGame.main.ReadyChecker;

public abstract class Update extends Timer {
    protected Game game = null;

    public Update(double updateRate) {
        super(updateRate);
    }

    public void setGame(Game game) {
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
