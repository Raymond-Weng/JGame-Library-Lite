package jGame.loop.render;

import jGame.Game;
import jGame.debug.Stat;

import java.awt.*;

public class RenderImpl extends Render {
    private Game game = null;

    /**
     * create the object
     *
     * @param maxFps the maximum fps, the maximum frame per second, is different from update rate
     */
    public RenderImpl(double maxFps) {
        super(1d / maxFps);
    }

    public void setGame(Game game) {
        this.game = game;
        Stat.setStatBoolean(Stat.RENDER_READY, true);
    }

    @Override
    public void renderGame() {
        this.game.getObjects().forEach(arrayList -> {
            arrayList.forEach(gameObject -> {
                Image image = gameObject.render();
                this.game.getOutput().getGraphics().drawImage(image, gameObject.getPosition().getIntX(), gameObject.getPosition().getIntY(), null);
            });
        });
    }

    //TODO
}
