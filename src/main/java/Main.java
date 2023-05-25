import jGame.Game;
import jGame.core.Size;
import jGame.loop.RenderImpl;
import jGame.loop.UpdateImpl;
import jGame.output.Frame;

public class Main {
    public static void main(String[] args) {
        Game game = new Game.Builder()
                .setOutput(new Frame(new Size(800d, 600d)))
                .setRender(new RenderImpl())
                .setUpdate(new UpdateImpl())
                .build();
        game.build();
        game.run();
    }
}
