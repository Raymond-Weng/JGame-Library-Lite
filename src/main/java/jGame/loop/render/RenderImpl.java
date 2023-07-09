package jGame.loop.render;

import jGame.main.Game;
import jGame.main.ReadyChecker;

import java.awt.*;

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

        this.game.getObjects().forEach(arrayList ->
                arrayList.forEach(gameObject -> {
                    Image image = gameObject.render();
                    if (new Rectangle(0, 0,
                            game.getOutput().getSize().getIntWidth(),
                            game.getOutput().getSize().getIntHeight())
                            .intersects(new Rectangle(
                                    gameObject.getPosition().getIntX(),
                                    gameObject.getPosition().getIntY(),
                                    image.getWidth(null),
                                    image.getHeight(null)))) {
                        this.game.getOutput().getGraphics().drawImage(image,
                                gameObject.getPosition().getIntX() - game.getCamera().getPosition().getIntX(),
                                gameObject.getPosition().getIntY() - game.getCamera().getPosition().getIntY(),
                                null);
                        this.game.getOutput().getGraphics().dispose();
                    }
                })
        );

        this.game.getOutput().getGraphics().dispose();
        this.game.getOutput().show();
    }

    public int getFps() {
        return super.getUps();
    }
}
