package exampleCode;

import jGame.main.Game;
import jGame.output.Output;
import jGame.loop.render.Render;
import jGame.loop.update.Update;

public class test {
    public static void main(String[] args) {
        Output output;
        Render render;
        Update update;

        Game game = new Game.Builder()
                .setOutput(output)
                .setRender(render)
                .setUpdate(update)
                .build();
    }
}
