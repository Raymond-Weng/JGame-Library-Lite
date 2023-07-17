package exampleCode;

import jGame.core.Size;
import jGame.debug.DebugStringHandler;
import jGame.loop.render.RenderImpl;
import jGame.loop.update.UpdateImpl;
import jGame.main.Game;
import jGame.output.Frame;
import jGame.output.listener.KeyListenerImpl;
import jGame.output.listener.MouseListenerImpl;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Main {
    private static Main main;

    public static Main get() {
        return main;
    }

    public Game game;
    public volatile MouseListenerImpl mouseListener;
    public volatile KeyListenerImpl keyListener;

    public static Player player;

    public static void main(String[] args) throws InterruptedException {
        MouseListenerImpl mouseListenerImpl = new MouseListenerImpl();
        KeyListenerImpl keyListenerImpl = new KeyListenerImpl();
        RenderImpl render = new RenderImpl(60);
        UpdateImpl update = new UpdateImpl(60);

        Frame output = new Frame.Builder()
                .setSize(new Size(1600d, 1000d))
                .setNumBufferStrategy(2)
                .setMouseListener(mouseListenerImpl)
                .setMouseMotionListener(mouseListenerImpl)
                .setKeyListener(keyListenerImpl)
                .build();
        Game game = new Game.Builder()
                .setDebug(true)
                .setOutput(output)
                .setRender(render)
                .setUpdate(update)
                .build();
        output.setGame(game);
        output.showFrame();

        render.setGame(game);
        update.setGame(game);

        main = new Main(game, mouseListenerImpl, keyListenerImpl);
    }

    public Main(Game game, MouseListenerImpl mouseListener, KeyListenerImpl keyListener) {
        this.game = game;
        this.mouseListener = mouseListener;
        this.keyListener = keyListener;

        game.build();

        debugSetup();

        Main.player = new Player(keyListener);
        game.addObject(player, 1);

        game.run();
    }

    private void debugSetup() {
        this.game.getDebugPanel().addVariable("Mouse Inside", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.get().mouseListener.isMouseInside());
            }
        });

        this.game.getDebugPanel().addVariable("Mouse Pressed", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.get().mouseListener.isMousePressed(MouseEvent.BUTTON1));
            }
        });
        this.game.getDebugPanel().addVariable("Mouse X", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.get().mouseListener.getMousePos().getX());
            }
        });
        this.game.getDebugPanel().addVariable("Mouse Y", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.get().mouseListener.getMousePos().getY());
            }
        });
        this.game.getDebugPanel().addVariable("A Pressed", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return String.valueOf(Main.get().keyListener.isKeyPressed(KeyEvent.VK_A));
            }
        });

        this.game.getDebugPanel().addVariable("Camera X, Y", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return game.getCamera().getPosition().getIntX() + "/" + game.getCamera().getPosition().getIntY();
            }
        });

        this.game.getDebugPanel().addVariable("Player X, Y", new DebugStringHandler() {
            @Override
            public String getText(Game game) {
                return Main.player.getPosition().getIntX() + "/" + Main.player.getPosition().getIntY();
            }
        });
    }
}
