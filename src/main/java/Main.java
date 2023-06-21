import jGame.Game;
import jGame.core.Size;
import jGame.gameObject.objects.Rectangle;
import jGame.loop.render.Render;
import jGame.loop.render.RenderImpl;
import jGame.loop.update.Update;
import jGame.loop.update.UpdateImpl;
import jGame.output.Frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = null;
        Frame output = null;
        RenderImpl render = new RenderImpl(60);
        UpdateImpl update = new UpdateImpl(60);
        output = new Frame.Builder()
                .setSize(new Size(800d, 600d))
                .setNumBufferStrategy(2)
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
    }
}
