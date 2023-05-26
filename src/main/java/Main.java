import jGame.Game;
import jGame.core.Size;
import jGame.exception.BuilderException;
import jGame.loop.render.RenderImpl;
import jGame.loop.update.UpdateImpl;
import jGame.output.Frame;

public class Main {
    public static void main(String[] args) {
        Game game = null;
        try {
            game = new Game.Builder()
                    .setOutput(new Frame(new Size(800d, 600d)))
                    .setRender(new RenderImpl(60))
                    .setUpdate(new UpdateImpl(60))
                    .build();
        } catch (BuilderException e) {
            throw new RuntimeException(e);
        }
        game.build();
        game.run();
    }
}
