package exampleCode;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;
import jGame.output.listener.KeyListenerImpl;
import jGame.core.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    KeyListenerImpl keyListener;

    Position position = new Position(0, 0);

    Vector movementInput = new Vector(0);
    Vector velocity = new Vector(0);
    double friction = 0.8;

    public Player(KeyListenerImpl keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public void update() {
//        double speed = 2;
//
//        if(keyListener.isKeyPressed(KeyEvent.VK_D) || keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
//            movementInput = movementInput.setX(1);
//        }
//        else if(keyListener.isKeyPressed(KeyEvent.VK_A) || keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
//            movementInput = movementInput.setX(-1);
//        }
//        else {
//            movementInput = movementInput.setX(0);
//        }
//        if(keyListener.isKeyPressed(KeyEvent.VK_W) || keyListener.isKeyPressed(KeyEvent.VK_UP)) {
//            movementInput = movementInput.setY(-1);
//        }
//        else if(keyListener.isKeyPressed(KeyEvent.VK_S) || keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
//            movementInput = movementInput.setY(1);
//        }
//        else {
//            movementInput = movementInput.setY(0);
//        }
//
//        velocity = velocity.add(Vector.multiply(movementInput.normalized(), speed));
//        velocity = velocity.multiply(friction);
//
//        position = position.add(velocity);
    }

    @Override
    public Image render() {
        Image image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

        Graphics g = image.getGraphics();

        g.setColor(new Color(34, 171, 255));
        g.fillRect(0, 0, 50, 50);

        g.dispose();

        return image;
    }

    @Override
    public Position getPosition() {
        return position;
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