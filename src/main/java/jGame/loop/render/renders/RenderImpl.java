package jGame.loop.render.renders;

import jGame.core.Size;
import jGame.loop.render.Render;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * the simple render implement
 */
public class RenderImpl extends Render {
    /**
     * reused every frame instead of creating a new one, recreated only when the display area changes size
     */
    private BufferedImage image;

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
        int width = game.getCamera().getDisplayArea().getIntWidth();
        int height = game.getCamera().getDisplayArea().getIntHeight();
        if (image == null || image.getWidth() != width || image.getHeight() != height) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D imageGraphics = image.createGraphics();
        // clear last frame to transparent, the background color is filled in when drawing to the output
        imageGraphics.setComposite(AlphaComposite.Clear);
        imageGraphics.fillRect(0, 0, width, height);
        imageGraphics.setComposite(AlphaComposite.SrcOver);

        Rectangle displayArea = new Rectangle(game.getCamera().getPosition().subtract(game.getCamera().getDisplayArea().divide(2d)).toPoint(), game.getCamera().getDisplayArea().toDimension());

        synchronized (this.game.getObjects()) {
            this.game.getObjects().forEach(arrayList ->
                    arrayList.forEach(gameObject -> {
                        Image object = gameObject.render();
                        if (object == null) {
                            return;
                        }
                        Size objectSize = new Size(object.getWidth(null), object.getHeight(null));
                        if(displayArea.intersects(
                                new Rectangle(gameObject.getPosition().subtract(objectSize.divide(2d)).toPoint(), objectSize.toDimension())
                        )){
                            imageGraphics.drawImage(
                                    object,
                                    gameObject.getPosition().getIntX() - (object.getWidth(null)/2) - game.getCamera().getPosition().getIntX() + (game.getCamera().getDisplayArea().getIntWidth()/2),
                                    gameObject.getPosition().getIntY() - (object.getHeight(null)/2) - game.getCamera().getPosition().getIntY() + (game.getCamera().getDisplayArea().getIntHeight()/2),
                                    null
                            );
                        }
                    })
            );
        }

        imageGraphics.dispose();

        Graphics outputGraphics = game.getOutput().getGraphics();
        outputGraphics.drawImage(image,
                0,
                0,
                game.getOutput().getSize().getIntWidth(),
                game.getOutput().getSize().getIntHeight(),
                game.BACKGROUND_COLOR,
                null);
        outputGraphics.dispose();
        game.getOutput().show();

        this.updateTime++;
    }
}
