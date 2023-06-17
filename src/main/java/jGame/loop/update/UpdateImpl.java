package jGame.loop.update;

import jGame.Game;
import jGame.debug.Stat;
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
        this.game = game;
    }

    public void setGame(Game game) {
        this.game = game;
        Stat.setStatBoolean(Stat.UPDATE_READY, true);
    }

    @Override
    public void updateGame() {
        this.game.getObjects().forEach(arrayList -> {
            arrayList.forEach(GameObject::render);
        });
    }

    //TODO
}
