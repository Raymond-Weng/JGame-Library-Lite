/**
 * The main object to control whole game.
 */

package jGame;

import jGame.debug.Stat;
import jGame.exception.BuilderException;
import jGame.exception.PriorityException;
import jGame.exception.TimeOutException;
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
    /**
     * the thing to create game object, please read the docs of every method
     *
     * *must contain args : output, render, update
     */
    public static class Builder {
        private Output output = null;

        /**
         * [must contain]the output of the game
         *
         * @param output the output of the game, please implement it
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         * @see Output
         * @see jGame.output.Frame
         */
        public Builder setOutput(Output output) {
            this.output = output;
            return this;
        }

        private Render render = null;

        /**
         * [must contain]set the render class to render the gameObjects
         *
         * @param render extend the render to overwrite the things in render
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         * @see Render
         * @see jGame.loop.render.RenderImpl
         */
        public Builder setRender(Render render) {
            this.render = render;
            return this;
        }

        private Update update = null;

        /**
         * [nust contain]set the update class to update the gameObjects
         *
         * @param update extend the update to overwrite the things in update
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         * @see Update
         * @see UpdateImpl
         */
        public Builder setUpdate(Update update) {
            this.update = update;
            return this;
        }

        private int threadCount = -1;

        /**
         * set the thread count
         * @param threadCount how many thread you need, this will be set to 2 if you the arg is less than 2
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setThreadCount(int threadCount) {
            this.threadCount = threadCount;
            return this;
        }

        private int loadingTimeOut = -1;

        /**
         * the max loading time, start counting down when the method {@code run()} was called
         * @param loadingTimeOut the max loading time
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setLoadingTimeOut(int loadingTimeOut) {
            this.loadingTimeOut = loadingTimeOut;
            return this;
        }

        /**
         * create the game object
         * @return
         */
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
                        Math.max(threadCount, 2),
                        (loadingTimeOut == -1) ? 10 : loadingTimeOut
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
    private int loadingTimeOut;

    private Game(Output output,
                 Render render,
                 Update update,
                 int threadCount,
                 int loadingTimeOut) {
        this.output = output;
        this.loadingTimeOut = loadingTimeOut;

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

        double startTime = System.currentTimeMillis();
        while (!(
                Stat.getStatBoolean(Stat.OUTPUT_READY) ||
                        Stat.getStatBoolean(Stat.RENDER_READY) ||
                        Stat.getStatBoolean(Stat.UPDATE_READY)
        )) {
            if ((System.currentTimeMillis() - startTime) > (loadingTimeOut * 1000)) {
                throw new TimeOutException("Time out, loading should finish in " + loadingTimeOut + " second.");
            }
        }

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
