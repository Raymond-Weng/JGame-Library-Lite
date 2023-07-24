package exampleCode.Objects;

import exampleCode.SceneController;
import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.hitbox.Hitbox;
import jGame.loop.render.CameraImpl;
import jGame.output.Frame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Score extends GameObject {

    private final Frame frame;
    private final SceneController scene;
    private final CameraImpl camera;

    private long score = 0;

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
            score = 0;
        }
    }

    @Override
    public Image render() {
        Image image = new BufferedImage(frame.getSize().getIntWidth(), frame.getSize().getIntHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics g = image.getGraphics();

        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.BOLD, 48));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(String.valueOf(score), frame.getSize().getIntWidth() / 2 - fm.stringWidth(String.valueOf(score)) / 2, 70);

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
