/**
 * The main object to control whole game.
 */

package jGame;

import jGame.exception.BuilderException;
import jGame.exception.PriorityException;
import jGame.gameObject.GameObject;
import jGame.loop.render.Render;
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

        private int threadCount = -1;

        public Builder setThreadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }


        public Game build() {
            if (
                    output != null
                            || render != null
                            || update != null
            )
                return new Game(
                        output,
                        render,
                        update,
                        Math.max(threadCount, 2)
                );
            else
                throw new BuilderException("There is some missing args.");
        }
    }

    public volatile boolean loading = false;

    private final ArrayList<ArrayList<GameObject>> objects;

    private Output output;
    private TimerManager[] timerManagers = new TimerManager[10];

    private GameThread[] gameThreads;
    private GameThread gameThread;

    private Game(Output output,
                 Render render,
                 Update update,
                 int threadCount) {
        this.output = output;

        timerManagers[0] = new TimerManager(render, update);

        gameThreads = new GameThread[threadCount];

        objects = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            objects.add(new ArrayList<>());
        }
    }

    /**
     * build the game, like loading output, reading file, etc.
     */
    public void build() {
        for (int i = 0; i < gameThreads.length; i++) {
            if (i != 0) {
                timerManagers[i] = new TimerManager();
            }

            gameThreads[i] = new GameThread(timerManagers[i]);
        }

        //TODO build of the game
    }

    /**
     * start the game
     */
    public void run() {
        for (int i = 0; i < gameThreads.length; i++) {
            gameThreads[i].start();
        }
        //TODO run method in Game

        this.loading = false;
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

    /**
     * get the output of the game
     *
     * @return the output of the game
     */
    public Output getOutput() {
        return output;
    }

    /**
     * add a timer to the thread
     *
     * @param timer    the timer which is going to be added
     * @param priority the update priority, it will be updated quicker if it smaller. (This should be between 0 and 9)
     * @throws PriorityException if the priority is not between 0 and 9, it will throw an exception.
     */
    public void addTimer(Timer timer, int priority) throws PriorityException {
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
                this.timerManagers[priority].addTimer(timer);
                break;
            default:
                throw new PriorityException("Property should between 0 and 9, but it is " + priority + ".");
        }
    }

    public ArrayList<ArrayList<GameObject>> getObjects() {
        return objects;
    }
}
