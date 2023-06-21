package jGame.gameObject.objects;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;

import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Rectangle extends GameObject {
    private Position position = new Position(0,0);

    @Override
    public void update() {
        position = new Position(position.getX()+1, position.getY()+1);
    }

    @Override
    public Image render() {
        Image image = new BufferedImage(50,50,BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().setColor(Color.WHITE);
        image.getGraphics().fillRect(0,0,50,50);
        image.getGraphics().dispose();
        return image;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public Size getSize() {
        return null;
    }
    //TODO
}
