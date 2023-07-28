package jGame.loop.render;

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
                        if (new Rectangle(game.getCamera().getPosition().getIntX() - (game.getCamera().getDisplayArea().getIntWidth()/2),
                                game.getCamera().getPosition().getIntY() - (game.getCamera().getDisplayArea().getIntHeight()/2),
                                game.getCamera().getDisplayArea().getIntWidth(),
                                game.getCamera().getDisplayArea().getIntHeight())
                                .intersects(new Rectangle(
                                        gameObject.getPosition().getIntX() - (object.getWidth(null) / 2),
                                        gameObject.getPosition().getIntY() - (object.getHeight(null) / 2),
                                        object.getWidth(null),
                                        object.getHeight(null)))) {
                            image.getGraphics().drawImage(object,
                                    gameObject.getPosition().getIntX() - (object.getWidth(null) / 2) - game.getCamera().getPosition().getIntX() + (game.getCamera().getDisplayArea().getIntWidth()/2),
                                    gameObject.getPosition().getIntY() - (object.getHeight(null) / 2) - game.getCamera().getPosition().getIntY() + (game.getCamera().getDisplayArea().getIntHeight()/2),
                                    null);
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
