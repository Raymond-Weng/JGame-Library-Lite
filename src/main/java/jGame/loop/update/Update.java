package jGame.loop.update;

import jGame.loop.timer.Timer;

public abstract class Update extends Timer {
    public Update(double updateRate) {
        super(updateRate);
    }

    @Override
    public void action(){
        updateGame();
    }

    /**
     * update once (compute position, size...)
     */
    public abstract void updateGame();
}
