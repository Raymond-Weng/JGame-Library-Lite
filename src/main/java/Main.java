import jGame.Game;
import jGame.core.Size;
import jGame.gameObject.objects.Rectangle;
import jGame.loop.render.RenderImpl;
import jGame.loop.update.UpdateImpl;
import jGame.output.Frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = null;
        Frame output = null;
        output = new Frame.Builder()
                .setSize(new Size(800d, 600d))
                .setNumBufferStrategy(2)
                .build();
        game = new Game.Builder()
                .setOutput(output)
                .setRender(new RenderImpl(60))
                .setUpdate(new UpdateImpl(game, 60))
                .build();
        output.showFrame(game);
        game.build();
        game.run();
        System.out.println("test");
        game.addObject(new Rectangle(), 1);
    }
}
