package jGame.gameObject.hitbox;

import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.hitbox.hitboxShape.Rectangle;
import jGame.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HitboxTracker extends GameObject {
    private final Game game;
    private final ArrayList<GameObject> gameObjects;
    private final boolean work;

    public HitboxTracker(Game game) {
        this.game = game;
        gameObjects = new ArrayList<>();
        work = true;
    }

    public HitboxTracker() {
        game = null;
        gameObjects = null;
        work = false;
    }

    @Override
    public void update() {

    }

    @Override
    public Image render() {
        if (work) {
            // this image is drawn at the camera position in world space, so it must match the camera's display area,
            // not the output size (the render scales the display area to the output afterwards)
            Size displayArea = game.getCamera().getDisplayArea();
            Position cameraPosition = game.getCamera().getPosition();
            Image image = new BufferedImage(displayArea.getIntWidth(),
                    displayArea.getIntHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics graphics = image.getGraphics();
            graphics.setColor(new Color(255, 0, 0, 255));
            synchronized (gameObjects) {
                gameObjects.forEach(gameObject -> {
                    Hitbox<?> objectHitbox = gameObject.getHitbox();
                    if (objectHitbox == null) {
                        return;
                    }
                    objectHitbox.getHitboxes().forEach(hitbox -> {
                        if (hitbox.cannotHit() || !(hitbox.getShape() instanceof Rectangle shape)) {
                            return;
                        }
                        java.awt.Rectangle rectangle = shape.getRectangle();
                        graphics.drawRect(
                                rectangle.x - cameraPosition.getIntX() + (displayArea.getIntWidth() / 2) - 1,
                                rectangle.y - cameraPosition.getIntY() + (displayArea.getIntHeight() / 2) - 1,
                                rectangle.width + 2,
                                rectangle.height + 2
                        );
                    });
                });
            }
            graphics.dispose();

            return image;
        } else {
            return null;
        }
    }

    @Override
    public Position getPosition() {
        return game.getCamera().getPosition();
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public Size getSize() {
        return new Size(1, 1);
    }

    public void track(GameObject gameObject) {
        if(this.work){
            synchronized (gameObjects) {
                this.gameObjects.add(gameObject);
            }
        }
    }
}
