package exampleCode.Objects;

import exampleCode.SceneController;
import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.hitbox.Hitbox;
import jGame.loop.render.cameras.CameraImpl;
import jGame.output.Frame;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.max;

public class Score extends GameObject {

    private final Frame frame;
    private final SceneController scene;
    private final CameraImpl camera;

    private long score = 0;
    private long bs = 0;

    public Score(Frame frame, SceneController scene, CameraImpl camera) {
        this.frame = frame;
        this.scene = scene;
        this.camera = camera;
    }

    @Override
    public void update() {
        if(scene.getScene().equals("Game")) {
            score += 1;
        }
        else if(scene.getScene().equals("Menu")){
            bs = max(bs, score);
            score = 0;
        }
    }

    private static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 48);
    private static final Font BEST_SCORE_FONT = new Font("Arial", Font.BOLD, 32);

    private BufferedImage image;
    // what the image currently shows, -1 means it hasn't been drawn yet
    private long drawnScore = -1;
    private long drawnBs = -1;

    @Override
    public Image render() {
        if (image == null) {
            image = new BufferedImage(frame.getSize().getIntWidth(), frame.getSize().getIntHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        if (score == drawnScore && bs == drawnBs) {
            return image;
        }
        drawnScore = score;
        drawnBs = bs;

        Graphics2D g = image.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.setComposite(AlphaComposite.SrcOver);

        g.setColor(new Color(255, 255, 255));
        g.setFont(SCORE_FONT);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(String.valueOf(score), frame.getSize().getIntWidth() / 2 - fm.stringWidth(String.valueOf(score)) / 2, 70);
        if(bs != 0){
            g.setFont(BEST_SCORE_FONT);
            fm = g.getFontMetrics();
            g.drawString("Best Score: "+ bs, frame.getSize().getIntWidth() / 2 - fm.stringWidth("Best Score: "+ bs) / 2, 120);
        }

        g.dispose();

        return image;
    }

    @Override
    public Position getPosition() {
        return camera.getPosition();
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public Size getSize() {
        return null;
    }
}
