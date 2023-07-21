package exampleCode.Objects;

import exampleCode.SceneController;
import exampleCode.math.PositionConverter;
import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;
import jGame.gameObject.MultiHitbox;
import jGame.gameObject.hitboxShape.Rectangle;
import jGame.loop.render.CameraImpl;
import jGame.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pipe extends GameObject {

    private final Game game;
    private final jGame.output.Frame frame;
    private final SceneController scene;
    private final CameraImpl camera;
    private final Player player;

    private Position position;
    private final Position initialPosition;

    private double gapSize;
    private double gapY;

    public Pipe(Game game, jGame.output.Frame frame, SceneController scene, CameraImpl camera, Player player, Position position) {
        this.game = game;
        this.frame = frame;
        this.scene = scene;
        this.camera = camera;
        this.player = player;

        initialPosition = position;
        this.position = position;

        gapSize = Math.random() * 50 + 150;  // Range(150, 200);
        gapY = (Math.random() - 0.5) * (frame.getSize().getHeight() - gapSize * 2);
    }

    @Override
    public void update() {
        if(scene.getScene().equals("Game")) {
            if (position.getX() < camera.getPosition().getX() - frame.getSize().getWidth() / 2 - (double) 75 / 2) {
                position = new Position(camera.getPosition().getX() + frame.getSize().getWidth() / 2 + 75, 0);

                gapSize = Math.random() * 50 + 150;  // Range(150, 200);
                gapY = (Math.random() - 0.5) * (frame.getSize().getHeight() - gapSize * 2);
            }
        }
        else if(scene.getScene().equals("Menu")) {
            position = initialPosition;
        }

        MultiHitbox<Rectangle> multiHitbox = new MultiHitbox<>();
        multiHitbox.addHitbox(new Hitbox<>(new Rectangle(new Position(position.getX() - 75 / 2, -frame.getSize().getHeight() / 2),
                                                         new Position(position.getX() + 75 / 2, gapY - gapSize / 2))));
        multiHitbox.addHitbox(new Hitbox<>(new Rectangle(new Position(position.getX() - 75 / 2, gapY + gapSize / 2),
                                                         new Position(position.getX() + 75 / 2, frame.getSize().getHeight() / 2))));

        if(multiHitbox.isHit(player.getHitbox())) {
            scene.setScene("Menu");
        }
    }

    @Override
    public Image render() {
        Image image =  new BufferedImage(75, 540, BufferedImage.TYPE_INT_ARGB);

        Graphics g = image.getGraphics();

        PositionConverter pc = new PositionConverter(frame);

        g.setColor(new Color(101, 255, 89));
        g.fillRect(0, 0, 75, pc.jGameToJFrame(new Position(0, gapY - gapSize / 2)).getIntY());
        g.fillRect(0, pc.jGameToJFrame(new Position(0, gapY + gapSize / 2)).getIntY(), 75, (int) (frame.getSize().getHeight() / 2 - gapY + gapSize / 2));

        return image;
    }

    @Override
    public Position getPosition() {
        return position;
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
