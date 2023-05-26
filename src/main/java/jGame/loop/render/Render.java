package jGame.loop.render;

import jGame.loop.timer.Timer;

public abstract class Render extends Timer {
    public Render(double updateRate) {
        super(updateRate);
    }

    @Override
    public void action(){
        renderGame();
    }

    /**
     * render one frame
     */
    public abstract void renderGame();
}
