package exampleCode;

import jGame.core.Position;
import jGame.core.Size;
import jGame.core.Vector;
import jGame.gameObject.GameObject;
import jGame.gameObject.hitbox.Hitbox;
import jGame.loop.render.cameras.CameraImpl;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainLoop extends GameObject {

    private final SceneController scene;
    private final CameraImpl camera;

    public MainLoop(SceneController scene, CameraImpl camera) {
        this.scene = scene;
        this.camera = camera;
    }

    @Override
    public void update() {
        if (scene.getScene().equals("Game")) {
            camera.setPosition(Position.add(camera.getPosition(), new Vector(5, 0)));
        }
        else {
            camera.setPosition(new Position(0, 0));
        }
    }

    @Override
    public Image render() {
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public Position getPosition() {
        return new Position(0, 0);
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
