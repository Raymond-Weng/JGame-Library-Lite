import jGame.Game;
import jGame.core.Size;
import jGame.debug.DebugStringHandler;
import jGame.gameObject.objects.Rectangle;
import jGame.loop.render.Render;
import jGame.loop.render.RenderImpl;
import jGame.loop.update.Update;
import jGame.loop.update.UpdateImpl;
import jGame.output.Frame;
import jGame.output.listener.MouseListener;

import java.awt.event.MouseEvent;

public class Main {
    public static MouseListener mouseListener;
    public static void main(String[] args) throws InterruptedException {
        Main.mouseListener = new MouseListener();
        Game game = null;
        Frame output = null;
        RenderImpl render = new RenderImpl(60);
        UpdateImpl update = new UpdateImpl(60);
        output = new Frame.Builder()
                .setSize(new Size(800d, 600d))
                .setNumBufferStrategy(2)
                .setMouseListener(mouseListener)
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
                return String.valueOf(Main.mouseListener.isMouseInside());
            }
        });

        game.getDebugPanel().addVariable("Mouse Pressed", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.mouseListener.isMousePressed(MouseEvent.BUTTON1));
            }
        });
    }
}
