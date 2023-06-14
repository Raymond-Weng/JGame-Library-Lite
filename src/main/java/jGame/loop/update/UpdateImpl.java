package jGame.loop.update;

import jGame.Game;
import jGame.gameObject.GameObject;

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
        this.game.getObjects().forEach(arrayList -> {
            arrayList.forEach(GameObject::render);
        });
    }

    //TODO
}
