package jGame.loop.render;

import java.awt.*;

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
        this.game.getOutput().getGraphics().setColor(Color.BLACK);
        this.game.getOutput().getGraphics().fillRect(0, 0, game.getOutput().getSize().getIntWidth(), game.getOutput().getSize().getIntHeight());

        synchronized (this.game.getObjects()) {
            this.game.getObjects().forEach(arrayList ->
                    arrayList.forEach(gameObject -> {
                        Image image = gameObject.render();
                        if (new Rectangle(game.getCamera().getPosition().getIntX() - (game.getOutput().getSize().getIntWidth()/2),
                                game.getCamera().getPosition().getIntY() - (game.getOutput().getSize().getIntHeight()/2),
                                game.getOutput().getSize().getIntWidth(),
                                game.getOutput().getSize().getIntHeight())
                                .intersects(new Rectangle(
                                        gameObject.getPosition().getIntX() - (image.getWidth(null) / 2),
                                        gameObject.getPosition().getIntY() - (image.getHeight(null) / 2),
                                        image.getWidth(null),
                                        image.getHeight(null)))) {
                            this.game.getOutput().getGraphics().drawImage(image,
                                    gameObject.getPosition().getIntX() - (image.getWidth(null) / 2) - game.getCamera().getPosition().getIntX() + (game.getOutput().getSize().getIntWidth()/2),
                                    gameObject.getPosition().getIntY() - (image.getHeight(null) / 2) - game.getCamera().getPosition().getIntY() + (game.getOutput().getSize().getIntHeight()/2),
                                    null);
                            this.game.getOutput().getGraphics().dispose();
                        }
                    })
            );
        }

        this.game.getOutput().getGraphics().dispose();
        this.game.getOutput().show();
    }
}
