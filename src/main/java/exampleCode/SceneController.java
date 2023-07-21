package exampleCode;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;
import jGame.output.listener.KeyListenerImpl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class SceneController extends GameObject {

    private final KeyListenerImpl keyListener;

    private String scene = "Menu";

    public SceneController(KeyListenerImpl keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public void update() {
        if(scene.equals("Menu")) {
            if(keyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
                scene = "Game";
            }
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

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
