package jGame.gameObject.objects;

import com.sun.istack.internal.NotNull;
import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;
import javafx.geometry.Pos;

import java.awt.Image;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Rectangle extends GameObject {
    private Position position;
    private Size size;
    private Color color;

    private Hitbox hitbox;

    public Rectangle(){
        this.position = new Position(0d, 0d);
        this.size = new Size(50d, 50d);
        this.color = Color.WHITE;

        hitbox = new Hitbox(position, size);
    }

    @Override
    public void update() {
    }

    @Override
    public Image render() {
        Image image = new BufferedImage(size.getIntWidth(), size.getIntHeight(), BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().setColor(color);
        image.getGraphics().fillRect(position.getIntX(), position.getIntY(), size.getIntWidth(), size.getIntHeight());
        image.getGraphics().dispose();
        return image;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public Size getSize() {
        return size;
    }

    public void setPosition(@NotNull Position position){
        this.position = position;
        hitbox.setPosition(position);
    }

    public void setSize(@NotNull Size size){
        this.size = size;
        hitbox.setSize(size);
    }

    public void setColor(@NotNull Color color){
        this.color = color;
    }
}
