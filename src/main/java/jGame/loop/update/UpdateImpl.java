package jGame.loop.update;

import jGame.Game;

public class UpdateImpl extends Update {
    private Game game;

    /**
     * create the object
     * @param game the game to get the objects
     * @param maxUps the maximum ups, the maximum update per second, is different from update rate
     */
    public UpdateImpl(Game game, double maxUps) {
        super(1d / maxUps);
        this.game = game;
    }

    @Override
    public void updateGame() {

    }

    //TODO
}
