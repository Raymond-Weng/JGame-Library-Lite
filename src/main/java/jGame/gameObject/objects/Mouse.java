package jGame.gameObject.objects;

import com.sun.istack.internal.NotNull;
import jGame.core.Position;
import jGame.core.Size;
import jGame.gameObject.GameObject;
import jGame.gameObject.Hitbox;
import jGame.output.listener.MouseListener;

import java.awt.Image;
import java.awt.event.MouseEvent;

public class Mouse extends GameObject {
    private Image mousePressed;
    private Image mouseReleased;
    private MouseListener mouseListener;
    private Position position;

    public Mouse(@NotNull Image mousePressed, @NotNull Image mouseReleased, @NotNull MouseListener mouseListener) {
        this.mousePressed = mousePressed;
        this.mouseReleased = mouseReleased;
        this.mouseListener = mouseListener;

        this.position = mouseListener.getMousePos();
    }

    public Mouse(@NotNull Image mouse, @NotNull MouseListener mouseListener) {
        this(mouse, mouse, mouseListener);
    }

    @Override
    public void update() {
        this.position = mouseListener.getMousePos();
    }

    @Override
    public Image render() {
        return (mouseListener.isMousePressed(MouseEvent.BUTTON1)) ? mousePressed : mouseReleased;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox(position, new Size(mousePressed.getWidth(null), mousePressed.getHeight(null)));
    }

    @Override
    public Size getSize() {
        return new Size(mousePressed.getWidth(null), mousePressed.getHeight(null));
    }
}
