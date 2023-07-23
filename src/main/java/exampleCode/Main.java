package exampleCode;

import exampleCode.Objects.Pipe;
import exampleCode.Objects.Player;
import exampleCode.Objects.PressSpaceToStart;
import exampleCode.Objects.Score;
import jGame.core.Position;
import jGame.core.Size;

import jGame.loop.render.CameraImpl;
import jGame.loop.render.Render;
import jGame.loop.render.RenderImpl;
import jGame.loop.update.Update;
import jGame.loop.update.UpdateImpl;
import jGame.main.Game;
import jGame.output.Frame;
import jGame.output.listener.KeyListenerImpl;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        KeyListenerImpl keyListener = new KeyListenerImpl();

        Frame frame = new Frame.Builder()
                .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
                .setSize(new Size(1920 / 2, 1080 / 2))
                .setFrameTitle("FlappyBird")

                .setFullScreen(true)

                .setKeyListener(keyListener)

                .build();

        CameraImpl camera = new CameraImpl(new Position(0, 0), frame.getSize());
        Render render = new RenderImpl(60);
        Update update = new UpdateImpl(60);

        Game game = new Game.Builder()
                .setDebug(true)
                .setOutput(frame)
                .setCamera(camera)
                .setRender(render)
                .setUpdate(update)

                .setBackgroundColor(new Color(128, 203, 255))

                .build();

        frame.setGame(game);
        frame.showFrame();  // It's loading screen at first.

        camera.setGame(game);
        render.setGame(game);
        update.setGame(game);

        game.build();

        Environment environment = new Environment(0.7);
        SceneController scene = new SceneController(keyListener);
        game.addObject(new MainLoop(scene, camera), 0);
        game.addObject(scene, 0);

        Player player = new Player(scene, environment, camera, keyListener);
        game.addObject(new Score(frame, scene, camera), 9);
        game.addObject(new PressSpaceToStart(frame, scene), 1);
        game.addObject(player, 8);
        game.addObject(new Pipe(game, frame, scene, camera, player, new Position(960 + 75, 0)), 2);
        game.addObject(new Pipe(game, frame, scene, camera, player, new Position(960 * 3 / 2 + 75, 0)), 2);

        game.run();
    }
}
