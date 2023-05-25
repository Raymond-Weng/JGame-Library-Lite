import jGame.Game;
import jGame.core.Size;
import jGame.exception.BuilderException;
import jGame.loop.RenderImpl;
import jGame.loop.UpdateImpl;
import jGame.output.Frame;

public class Main {
    public static void main(String[] args) {
        Game game = null;
        try {
            game = new Game.Builder()
                    .setOutput(new Frame(new Size(800d, 600d)))
                    .setRender(new RenderImpl())
                    .setUpdate(new UpdateImpl())
                    .build();
        } catch (BuilderException e) {
            throw new RuntimeException(e);
        }
        game.build();
        game.run();
    }
}
