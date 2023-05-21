import jGame.Game;
import jGame.core.Setting;
import jGame.core.Size;
import jGame.output.Output;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Setting() {
            @Override
            public Size frameSize() {
                return new Size(800.0, 600.0);
            }

            @Override
            public Output output() {
                return null;
            }
        });
        game.build();
        game.run();
    }
}
