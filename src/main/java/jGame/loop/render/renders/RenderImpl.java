package jGame.loop.render.renders;

import jGame.core.Size;
import jGame.core.Vector;
import jGame.loop.render.Render;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * the simple render implement
 */
public class RenderImpl extends Render {
    /**
     * create the object
     *
     * @param maxFps the maximum fps, the maximum frame per second, is different from update rate
     */
    public RenderImpl(double maxFps) {
        super(1d / maxFps);
    }

    @Override
    public void renderGame() {
        Image image = new BufferedImage(game.getCamera().getDisplayArea().getIntWidth(),
                game.getCamera().getDisplayArea().getIntHeight(),
                BufferedImage.TYPE_INT_ARGB);

        synchronized (this.game.getObjects()) {
            this.game.getObjects().forEach(arrayList ->
                    arrayList.forEach(gameObject -> {
                        Image object = gameObject.render();
                        if(new Rectangle(game.getCamera().getPosition().subtract(game.getCamera().getDisplayArea().divide(2d)).toPoint(), game.getCamera().getDisplayArea().toDimension()).intersects(
                                new Rectangle(gameObject.getPosition().subtract(new Size(image.getWidth(null), image.getHeight(null)).divide(2d)).toPoint(), new Dimension(image.getWidth(null), image.getHeight(null)))
                        )){
                            image.getGraphics().drawImage(
                                    object,
                                    gameObject.getPosition().getIntX() - (object.getWidth(null)/2) - game.getCamera().getPosition().getIntX() + (game.getCamera().getDisplayArea().getIntWidth()/2),
                                    gameObject.getPosition().getIntY() - (object.getHeight(null)/2) - game.getCamera().getPosition().getIntY() + (game.getCamera().getDisplayArea().getIntHeight()/2),
                                    null
                            );
                            image.getGraphics().dispose();
                        }
                    })
            );
        }

        image.getGraphics().dispose();

        game.getOutput().getGraphics().drawImage(image,
                0,
                0,
                game.getOutput().getSize().getIntWidth(),
                game.getOutput().getSize().getIntHeight(),
                game.BACKGROUND_COLOR,
                null);
        game.getOutput().show();

        this.updateTime++;
    }
}
