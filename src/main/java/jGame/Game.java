/**
 * The main object to control whole game.
 */

package jGame;

import jGame.exception.BuilderException;
import jGame.exception.PriorityException;
import jGame.gameObject.GameObject;
import jGame.loop.render.Render;
import jGame.loop.render.RenderImpl;
import jGame.loop.timer.GameThread;
import jGame.loop.timer.Timer;
import jGame.loop.timer.TimerManager;
import jGame.loop.update.Update;
import jGame.loop.update.UpdateImpl;
import jGame.output.Output;

import java.util.ArrayList;

public class Game {
    public static class Builder {
        private Output output = null;

        public Builder setOutput(Output output) {
            this.output = output;
            return this;
        }

        private Render render = null;

        public Builder setRender(Render render) {
            this.render = render;
            return this;
        }

        private Update update = null;

        public Builder setUpdate(Update update) {
            this.update = update;
            return this;
        }

        private double maxFps = -1;

        public Builder setMaxFps(double maxFps) {
            this.maxFps = maxFps;
            return this;
        }

        private double maxUps = -1;

        public Builder setMaxUps(double maxUps) {
            this.maxUps = maxUps;
            return this;
        }


        public Game build() throws BuilderException {
            if (
                    output != null
            )
                return new Game(
                        output,
                        (render == null) ? new RenderImpl((maxFps == -1) ? 60 : maxFps) : render,
                        (update == null) ? new UpdateImpl((maxUps == -1) ? 60 : maxUps) : update
                );
            else
                throw new BuilderException("There is some missing args.");
        }
    }
    public volatile boolean loading = false;

    private final ArrayList<ArrayList<GameObject>> objects;

    private Output output;
    private TimerManager timerManager;
    private GameThread gameThread;

    private Game(Output output,
                Render render,
                Update update) {
        this.output = output;

        timerManager = new TimerManager(render, update);

        objects = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            objects.add(new ArrayList<>());
        }
    }

    /**
     * build the game, like loading output, reading file, etc.
     */
    public void build() {
        GameThread gameThread1 = new GameThread(timerManager);

        //TODO build of the game
    }

    /**
     * start the game
     */
    public void run() {
        this.loading = false;

        //TODO game start method
    }

    /**
     * add an object to the list, start rendering and updating.
     *
     * @param gameObject the object which is going to be added
     * @param priority   the render (or update) priority, it will be rendered (or updated) quicker if it bigger. (This should be between 0 and 9)
     * @throws PriorityException if the priority is not between 0 and 9, it will throw an exception.
     */
    public void addObject(GameObject gameObject, int priority) throws PriorityException {
        switch (priority) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                this.objects.get(priority).add(gameObject);
                break;
            default:
                throw new PriorityException("Property should between 0 and 9, but it is " + priority + ".");
        }
    }

    public Output getOutput() {
        return output;
    }
}
