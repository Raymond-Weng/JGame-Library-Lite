package jGame.loop.update;

import jGame.core.Position;
import jGame.core.Size;

import java.awt.*;

public class ScopeUpdate extends Update {
    private volatile Position position;
    private volatile Size scope;

    /**
     * create a update object, note that this scope count the "position" of the object
     *
     * @param updateRateSecond the update rate of this
     * @param center the center position of the update area
     * @param scope the scope of the update area
     */
    public ScopeUpdate(double updateRateSecond, Position center, Size scope) {
        super(updateRateSecond);
        this.position = center;
        this.scope = scope;
    }

    @Override
    public void updateGame() {
        synchronized (this.game.getObjects()) {
            synchronized (this.game.getObjects()) {
                this.game.getObjects().forEach(arrayList -> {
                    arrayList.forEach(gameObject -> {
                        if (new Rectangle(position.getIntX(),
                                position.getIntY(),
                                scope.getIntWidth(),
                                scope.getIntHeight())
                                .contains(new Point(gameObject.getPosition().getIntX(),
                                        gameObject.getPosition().getIntY()))) {
                            gameObject.update();
                        }
                    });
                });
            }
        }
    }

    /**
     * set the center position of the scope
     * @param center the center position of the scope
     */
    public void setCenter(Position center) {
        this.position = center;
    }

    /**
     * set the size of the scope
     * @param scope the size of the scope
     */
    public void setScope(Size scope) {
        this.scope = scope;
    }

    /**
     * set the position of the scope
     * @return the new position of the scope
     */
    public Position getCenter() {
        return this.position;
    }

    /**
     * set the size of the scope
     * @return the new size of the scope
     */
    public Size getScope(){
        return this.scope;
    }
}
