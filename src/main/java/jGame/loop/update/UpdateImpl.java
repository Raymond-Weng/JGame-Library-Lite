package jGame.loop.update;

import jGame.gameObject.GameObject;

public class UpdateImpl extends Update {
    /**
     * create the object
     *
     * @param maxUps the maximum ups, the maximum update per second, is different from update rate
     */
    public UpdateImpl(double maxUps) {
        super(1d / maxUps);
    }

    @Override
    public void updateGame() {
        synchronized (this.game.getObjects()) {
            synchronized (this.game.getObjects()) {
                this.game.getObjects().forEach(arrayList -> {
                    arrayList.forEach(GameObject::update);
                });
            }
        }
    }
}
