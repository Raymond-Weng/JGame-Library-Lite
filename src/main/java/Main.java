import jGame.Game;
import jGame.core.Setting;
import jGame.core.Size;
import jGame.loop.Render;
import jGame.loop.RenderImpl;
import jGame.loop.Update;
import jGame.loop.UpdateImpl;
import jGame.output.Frame;
import jGame.output.Output;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Setting() {

            @Override
            public Output getOutput() {
                return new Frame(new Size(800,600));
            }

            @Override
            public Render getRender() {
                return new RenderImpl();
            }

            @Override
            public Update getUpdate() {
                return new UpdateImpl();
            }
        });
        game.build();
        game.run();
    }
}
