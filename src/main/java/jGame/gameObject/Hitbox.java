package jGame.gameObject;

import jGame.core.Position;
import jGame.core.Size;

import java.awt.*;

public class Hitbox {
    private Rectangle rectangle;



    public Hitbox(Position position, Size size){
        rectangle = new Rectangle(position.getIntX(), position.getIntY(), size.getIntWidth(), size.getIntHeight());
    }

    public boolean isHit(Hitbox hitbox){


        return hitbox.getRectangle().intersects(this.rectangle);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
