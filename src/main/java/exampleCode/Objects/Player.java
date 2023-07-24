package exampleCode.Objects;

import exampleCode.Environment;
import exampleCode.SceneController;
import jGame.core.*;
import jGame.gameObject.GameObject;
import jGame.gameObject.hitbox.Hitbox;
import jGame.gameObject.hitbox.hitboxShape.Rectangle;
import jGame.loop.render.CameraImpl;
import jGame.output.listener.KeyListenerImpl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private final SceneController scene;
    private final CameraImpl camera;
    private final KeyListenerImpl keyListener;

    private final double GRAVITY;

    Position position = new Position(0, -50);
    Vector velocity = new Vector(0);

    private final double JUMPFORCE = 12;
    private boolean canPressSpace = true;

    public Player(SceneController scene, Environment environment, CameraImpl camera, KeyListenerImpl ketListener) {
        this.scene = scene;
        this.camera = camera;
        this.keyListener = ketListener;

        GRAVITY = environment.getGRAVITY();
    }

    @Override
    public void update() {
        if (scene.getScene().equals("Game")) {
            velocity = velocity.add(new Vector(0, GRAVITY));

            if (keyListener.isKeyPressed(KeyEvent.VK_SPACE) && canPressSpace) {
                canPressSpace = false;
                jump(JUMPFORCE);
            } else if (!keyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
                canPressSpace = true;
            }

            position = position.add(velocity);
            position = position.setX(camera.getPosition().getX());
        }
        else {
            position = new Position(0, -50);
        }
    }

    @Override
    public Image render() {
        Image image = new BufferedImage(35, 35, BufferedImage.TYPE_INT_ARGB);

        Graphics g = image.getGraphics();

        g.setColor(new Color(255, 227, 155));
        g.fillRect(0, 0, 35, 35);

        g.dispose();

        return image;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox<>(new Rectangle(position, new Size(35 - 3, 35 - 3)));
    }

    @Override
    public Size getSize() {
        return null;
    }

    public void jump(double force) {
        velocity = new Vector(0 , -force);
    }
}
