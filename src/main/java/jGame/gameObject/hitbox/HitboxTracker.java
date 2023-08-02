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
            Image image = new BufferedImage(game.getOutput().getSize().getIntWidth(),
                    game.getOutput().getSize().getIntHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            synchronized (gameObjects) {
                gameObjects.forEach(gameObject -> {
                    gameObject.getHitbox().getHitboxes().forEach(hitbox -> {
                        Graphics graphics = image.getGraphics();
                        graphics.setColor(new Color(255, 0, 0, 255));
                        graphics.drawRect(
                                ((Hitbox<Rectangle>) hitbox).getShape().getRectangle().x - game.getCamera().getPosition().getIntX() + (game.getOutput().getSize().getIntWidth() / 2) - 1,
                                ((Hitbox<Rectangle>) hitbox).getShape().getRectangle().y - game.getCamera().getPosition().getIntY() + (game.getOutput().getSize().getIntHeight() / 2) - 1,
                                ((Hitbox<Rectangle>) hitbox).getShape().getRectangle().width + 2,
                                ((Hitbox<Rectangle>) hitbox).getShape().getRectangle().height + 2
                        );
                        graphics.dispose();
                    });
                });

            }

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
            this.gameObjects.add(gameObject);
        }
    }
}
