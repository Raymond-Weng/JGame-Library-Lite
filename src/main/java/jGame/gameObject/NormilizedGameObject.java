package jGame.gameObject;

public abstract class NormilizedGameObject extends GameObject{
    public final void update(){
        //...

        this.objectUpdate();
    }

    protected abstract void objectUpdate();
}
