package jGame.loop.render;

import jGame.loop.timer.Timer;

public abstract class Render extends Timer {
    /**
     * create the object and set the rate
     * @param updateRate the rate of updating (for second), the max ups will be (1/updateRate)
     */
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
