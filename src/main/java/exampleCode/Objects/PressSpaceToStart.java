package exampleCode.Objects;

import exampleCode.SceneController;
import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PressSpaceToStart extends GameObject {

    private final jGame.output.Frame frame;
    private final SceneController scene;

    public PressSpaceToStart(jGame.output.Frame frame, SceneController scene) {
        this.scene = scene;
        this.frame = frame;
    }

    @Override
    public void update() {

    }

    @Override
    public Image render() {
        Image image = new BufferedImage(frame.getSize().getIntWidth(), frame.getSize().getIntHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics g = image.getGraphics();

        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.BOLD, 48));
        String text = "Press Space To Start";
        FontMetrics fm = g.getFontMetrics();
        g.drawString(text, (frame.getSize().getIntWidth() - fm.stringWidth(text)) / 2, (frame.getSize().getIntHeight() + fm.getHeight()) / 2 + fm.getHeight() * 2);
        g.dispose();

        return image;
    }

    @Override
    public Position getPosition() {
        return new Position(0, 0);
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
