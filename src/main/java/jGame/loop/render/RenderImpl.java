package jGame.loop.render;

import jGame.Game;

public class RenderImpl extends Render {
    private Game game;

    /**
     * create the object
     *
     * @param game the game object to get the objects
     * @param maxFps the maximum fps, the maximum frame per second, is different from update rate
     */
    public RenderImpl(Game game, double maxFps) {
        super(1d / maxFps);
        this.game = game;
    }

    @Override
    public void renderGame() {
    }

    //TODO
}
