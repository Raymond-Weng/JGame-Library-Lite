import jGame.Game;
import jGame.core.Setting;
import jGame.core.Size;
import jGame.output.Frame;
import jGame.output.Output;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Setting() {

            @Override
            public Output getOutput() {
                return new Frame(new Size(800,600));
            }
        });
        game.build();
        game.run();
    }
}
