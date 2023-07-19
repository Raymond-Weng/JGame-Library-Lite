package jGame.gameObject;

import jGame.gameObject.hitboxShape.Shape;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultiHitbox<E extends Shape> extends Hitbox<E> {
    private final ArrayList<Hitbox<E>> arrayList = new ArrayList<>();

    public void addHitbox(Hitbox<E> hitbox){
        arrayList.add(hitbox);
    }

    @Override
    public boolean isHit(Hitbox<E> hitbox){
        AtomicBoolean isHit = new AtomicBoolean(false);
        arrayList.forEach(hitbox1 -> {if(hitbox1.isHit(hitbox)){
                isHit.set(true);
            }
        });
        return isHit.get();
    }
}
