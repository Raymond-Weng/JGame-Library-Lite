package jGame.loop.update;

import jGame.main.Game;
import jGame.main.ReadyChecker;
import jGame.gameObject.GameObject;

public class UpdateImpl extends Update {
    private Game game;

    /**
     * create the object
     *
     * @param maxUps the maximum ups, the maximum update per second, is different from update rate
     */
    public UpdateImpl(double maxUps) {
        super(1d / maxUps);
    }

    public void setGame(Game game) {
        this.game = game;
        ReadyChecker.setStatBoolean(ReadyChecker.UPDATE_READY, true);
    }

    @Override
    public void updateGame() {
        this.game.getObjects().forEach(arrayList -> {
            arrayList.forEach(GameObject::update);
        });
    }

    //TODO
}
