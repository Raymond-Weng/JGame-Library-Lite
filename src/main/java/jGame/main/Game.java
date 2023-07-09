/**
 * The main object to control whole game.
 */

package jGame.main;

import jGame.debug.DebugPanel;
import jGame.debug.DebugStringHandler;
import jGame.exception.BuilderException;
import jGame.exception.PriorityException;
import jGame.exception.TimeOutException;
import jGame.gameObject.GameObject;
import jGame.loop.render.Camera;
import jGame.loop.render.NonCamera;
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
     * <p>*must contain args : output, render, update</p>
     */
    public static class Builder {
        private boolean debug = false;

        /**
         * set it {@code true} if you want debug mode on
         *
         * @param debug debug mode?
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

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
         *
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
         *
         * @param loadingTimeOut the max loading time
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setLoadingTimeOut(int loadingTimeOut) {
            this.loadingTimeOut = loadingTimeOut;
            return this;
        }

        private Camera camera = null;

        /**
         * the camera of the game, to look around the map
         *
         * @param camera the camera of the game
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setCamera(Camera camera) {
            this.camera = camera;
            return this;
        }

        private boolean ONLY_RENDER_AFTER_UPDATE = true;

        /**
         * set if the {@code render()} method of the Render class will only be called after the method {@code update} ran
         *
         * @param ONLY_RENDER_AFTER_UPDATE will it need to wait? (default value is {@code true}
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setOnlyRenderAfterUpdate(boolean ONLY_RENDER_AFTER_UPDATE) {
            this.ONLY_RENDER_AFTER_UPDATE = ONLY_RENDER_AFTER_UPDATE;
            return this;
        }

        /**
         * create the game object
         *
         * @return the game created
         */
        public Game build() {
            if (
                    output != null
                            || render != null
                            || update != null
            )
                return new Game(
                        debug,
                        output,
                        render,
                        update,
                        (camera == null) ? new NonCamera() : camera,
                        Math.max(threadCount, 2),
                        (loadingTimeOut == -1) ? 10 : loadingTimeOut,
                        ONLY_RENDER_AFTER_UPDATE
                );
            else
                throw new BuilderException("There is some missing args.");
        }
    }

    public final boolean ONLY_RENDER_AFTER_UPDATE;

    private boolean debug;

    public volatile boolean loading = false;

    private final ArrayList<ArrayList<GameObject>> objects;

    private final Output output;
    private TimerManager[] timerManagers;

    private GameThread[] gameThreads;
    private int loadingTimeOut;

    private DebugPanel debugPanel;
    private Camera camera;

    private Game(boolean debug,
                 Output output,
                 Render render,
                 Update update,
                 Camera camera,
                 int threadCount,
                 int loadingTimeOut,
                 boolean ONLY_RENDER_AFTER_UPDATE) {
        this.ONLY_RENDER_AFTER_UPDATE = ONLY_RENDER_AFTER_UPDATE;
        this.debug = debug;
        this.output = output;
        this.loadingTimeOut = loadingTimeOut;
        this.camera = camera;

        timerManagers = new TimerManager[threadCount];
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
        double startTime = System.currentTimeMillis();
        while (!(
                ReadyChecker.getStatBoolean(ReadyChecker.OUTPUT_READY) &&
                        ReadyChecker.getStatBoolean(ReadyChecker.RENDER_READY) &&
                        ReadyChecker.getStatBoolean(ReadyChecker.UPDATE_READY)
        )) {
            if ((System.currentTimeMillis() - startTime) > (loadingTimeOut * 1000)) {
                throw new TimeOutException("Time out, loading should finish in " + loadingTimeOut + " second.");
            }
        }

        for (GameThread thread : gameThreads) {
            thread.start();
        }

        if (debug) {
            this.debugPanel = new DebugPanel(this);
            this.debugPanel.addVariable("FPS", new DebugStringHandler() {
                @Override
                public String getText(Game game) {
                    return String.valueOf(game.timerManagers[0].getRender().getUps());
                }
            });
            this.debugPanel.addVariable("UPS", new DebugStringHandler() {
                @Override
                public String getText(Game game) {
                    return String.valueOf(game.timerManagers[0].getUpdate().getUps());
                }
            });

            this.debugPanel.start();
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
     * @param priority the update priority, it will be updated quicker if it smaller. (This should be smaller than the total of the threads)
     * @throws PriorityException if the priority is not between 0 and 9, it will throw an exception.
     */
    public void addTimer(Timer timer, int priority) throws PriorityException {
        if (priority < timerManagers.length) {
            this.timerManagers[priority].addTimer(timer);
        } else {
            throw new PriorityException("Property should between 0 and 9, but it is " + priority + ".");
        }
    }

    public ArrayList<ArrayList<GameObject>> getObjects() {
        return objects;
    }

    public GameThread getMainThread() {
        return gameThreads[0];
    }

    public boolean isDebug() {
        return debug;
    }

    public DebugPanel getDebugPanel() {
        return debugPanel;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
