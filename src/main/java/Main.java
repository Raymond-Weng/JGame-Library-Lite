import jGame.main.Game;
import jGame.core.Size;
import jGame.debug.DebugStringHandler;
import jGame.gameObject.objects.Rectangle;
import jGame.loop.render.RenderImpl;
import jGame.loop.update.UpdateImpl;
import jGame.output.Frame;
import jGame.output.listener.KeyListenerImpl;
import jGame.output.listener.MouseListenerImpl;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Main {
    public static MouseListenerImpl mouseListenerImpl;
    public static KeyListenerImpl keyListenerImpl;
    public static void main(String[] args) throws InterruptedException {
        Main.mouseListenerImpl = new MouseListenerImpl();
        Main.keyListenerImpl = new KeyListenerImpl();
        Game game = null;
        Frame output = null;
        RenderImpl render = new RenderImpl(60);
        UpdateImpl update = new UpdateImpl(60);
        output = new Frame.Builder()
                .setSize(new Size(800d, 600d))
                .setNumBufferStrategy(2)
                .setMouseListener(mouseListenerImpl)
                .setMouseMotionListener(mouseListenerImpl)
                .setKeyListener(keyListenerImpl)
                .build();
        game = new Game.Builder()
                .setDebug(true)
                .setOutput(output)
                .setRender(render)
                .setUpdate(update)
                .build();
        output.setGame(game);
        output.showFrame();
        game.build();

        render.setGame(game);
        update.setGame(game);

        game.run();
        game.addObject(new Rectangle(), 1);

        game.getDebugPanel().addVariable("Mouse Inside", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.mouseListenerImpl.isMouseInside());
            }
        });

        game.getDebugPanel().addVariable("Mouse Pressed", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.mouseListenerImpl.isMousePressed(MouseEvent.BUTTON1));
            }
        });
        game.getDebugPanel().addVariable("Mouse X", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.mouseListenerImpl.getMousePos().getX());
            }
        });
        game.getDebugPanel().addVariable("Mouse Y", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.mouseListenerImpl.getMousePos().getY());
            }
        });
        game.getDebugPanel().addVariable("A Pressed", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.keyListenerImpl.isKeyPressed(KeyEvent.VK_A));
            }
        });
    }
}
