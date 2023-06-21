package jGame.loop.render;

import jGame.Game;
import jGame.debug.Stat;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        //set up the image
        Image image = new BufferedImage(game.getOutput().getSize().getIntWidth(), game.getOutput().getSize().getIntHeight(), BufferedImage.TYPE_INT_RGB);
        image.getGraphics().setColor(Color.BLACK);
        image.getGraphics().fillRect(0, 0, game.getOutput().getSize().getIntWidth(), game.getOutput().getSize().getIntHeight());
        image.getGraphics().dispose();

        //render the objects on the image
        this.game.getObjects().forEach(arrayList ->
            arrayList.forEach(gameObject -> {
                image.getGraphics().drawImage(gameObject.render(), gameObject.getPosition().getIntX(), gameObject.getPosition().getIntY(), null);
                image.getGraphics().dispose();
            })
        );

        //render the image to the output
        this.game.getOutput().getGraphics().drawImage(image, 0, 0,  null);
        this.game.getOutput().getGraphics().dispose();
        this.game.getOutput().show();
    }

    public int getFps(){
        return super.getUps();
    }

    //TODO
}
