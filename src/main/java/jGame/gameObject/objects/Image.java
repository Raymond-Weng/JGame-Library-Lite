package jGame.gameObject.objects;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;

public class Image extends GameObject {
    private java.awt.Image image;

    public Image(java.awt.Image image, Position position){

    }

    @Override
    public void update() {}

    @Override
    public java.awt.Image render() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public Size getSize() {
        return null;
    }
}
