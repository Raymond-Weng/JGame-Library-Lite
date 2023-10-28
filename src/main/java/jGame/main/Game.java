package jGame.main;

import jGame.debug.DebugPanel;
import jGame.debug.DebugStringHandler;
import jGame.exception.BuilderException;
import jGame.exception.PriorityException;
import jGame.exception.TimeOutException;
import jGame.gameObject.GameObject;
import jGame.gameObject.hitbox.HitboxTracker;
import jGame.loop.render.cameras.Camera;
import jGame.loop.render.cameras.NonCamera;
import jGame.loop.render.Render;
import jGame.loop.render.renders.RenderImpl;
import jGame.loop.timer.GameThread;
import jGame.loop.timer.Timer;
import jGame.loop.timer.TimerManager;
import jGame.loop.update.Update;
import jGame.loop.update.updaters.UpdateImpl;
import jGame.output.Output;
import jGame.output.SecondLoading;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The main object to control whole game.
 */
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
         * @see RenderImpl
         */
        public Builder setRender(Render render) {
            this.render = render;
            return this;
        }

        private Update update = null;

        /**
         * [must contain]set the update class to update the gameObjects
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
         * @param threadCount how many thread you need, this will be set to 2 if the arg is less than 2
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

        private int fontSize = 20;

        /**
         * set the font size of the debug panel and loading page
         *
         * @param fontSize the size of the font
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setFontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        private Color backgroundColor = Color.black;

        /**
         * set the background color of the game
         *
         * @param backgroundColor the space without game object cover
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        private int gameStartDelay = 3;

        public Builder setGameStartDelay(int gameStartDelay) {
            this.gameStartDelay = gameStartDelay;
            return this;
        }

        private Image loadingImage = null;

        public Builder setLoadingImage(Image loadingImage) {
            this.loadingImage = loadingImage;
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
                            && render != null
                            && update != null
            ) {
                if (loadingImage == null) {
                    Image image = new BufferedImage(output.getSize().getIntWidth(),
                            output.getSize().getIntHeight(),
                            BufferedImage.TYPE_INT_RGB);

                    String text = "Game Library provided by RayJesse Studio";
                    Graphics graphics = image.getGraphics();
                    graphics.setColor(Color.black);
                    graphics.fillRect(0,
                            0,
                            output.getSize().getIntWidth(),
                            output.getSize().getIntHeight());
                    graphics.setColor(Color.white);
                    graphics.setFont(new Font(null, Font.PLAIN, fontSize));
                    int strW = graphics.getFontMetrics().stringWidth(text);
                    int stwH = graphics.getFontMetrics().getFont().getSize();
                    graphics.drawString(text, (output.getSize().getIntWidth() - strW) / 2,
                            (output.getSize().getIntHeight() - stwH) / 2);
                    graphics.dispose();
                    loadingImage = image;
                }
                return new Game(
                        debug,
                        output,
                        render,
                        update,
                        (camera == null) ? new NonCamera(output.getSize()) : camera,
                        Math.max(threadCount, 2),
                        (loadingTimeOut == -1) ? 10 : loadingTimeOut,
                        ONLY_RENDER_AFTER_UPDATE,
                        fontSize,
                        backgroundColor,
                        gameStartDelay,
                        loadingImage
                );
            } else {
                throw new BuilderException("There is some missing args.");
            }
        }
    }

    /**
     * should render run if the update hasn't run
     */
    public final boolean ONLY_RENDER_AFTER_UPDATE;

    /**
     * the size of the fonts
     */
    public final int FONT_SIZE;

    /**
     * the background color of the game
     */
    public final Color BACKGROUND_COLOR;

    /**
     * the ready time before the game start
     */
    public final int GAME_START_DELAY_SECOND;

    /**
     * is the game loading
     */
    public volatile boolean loading = false;

    /**
     * is the game loading
     */
    public volatile boolean second_loading = false;

    /**
     * the game object of the second loading page
     */
    public volatile SecondLoading secondLoading;

    public volatile HitboxTracker hitboxTracker;

    public final Image LOADING_IMAGE;

    private final boolean debug;
    private final ArrayList<ArrayList<GameObject>> objects;
    private final Output output;
    private final TimerManager[] timerManagers;
    private final GameThread[] gameThreads;
    private final int loadingTimeOut;
    private volatile DebugPanel debugPanel;
    private volatile Camera camera;
    private volatile ArrayList<ArrayList<GameObject>> objectsToBeAdded;
    private volatile ArrayList<ArrayList<GameObject>> objectsToBeRemoved;

    private Game(boolean debug,
                 Output output,
                 Render render,
                 Update update,
                 Camera camera,
                 int threadCount,
                 int loadingTimeOut,
                 boolean ONLY_RENDER_AFTER_UPDATE,
                 int FONT_SIZE,
                 Color backgroundColor,
                 int GAME_START_DELAY_SECOND,
                 Image LOADING_IMAGE) {
        this.ONLY_RENDER_AFTER_UPDATE = ONLY_RENDER_AFTER_UPDATE;
        this.debug = debug;
        this.output = output;
        this.loadingTimeOut = loadingTimeOut;
        this.camera = camera;
        this.FONT_SIZE = FONT_SIZE;
        this.BACKGROUND_COLOR = backgroundColor;
        this.GAME_START_DELAY_SECOND = GAME_START_DELAY_SECOND;
        this.LOADING_IMAGE = LOADING_IMAGE;

        timerManagers = new TimerManager[threadCount];
        timerManagers[0] = new TimerManager(render, update);

        gameThreads = new GameThread[threadCount];

        objects = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            objects.add(new ArrayList<>());
        }

        this.hitboxTracker = new HitboxTracker();
    }

    /**
     * build the game, like loading output, reading file, etc.
     */
    public void build() {
        objectsToBeAdded = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objectsToBeAdded.add(new ArrayList<>());
        }

        objectsToBeRemoved = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objectsToBeRemoved.add(new ArrayList<>());
        }

        for (int i = 0; i < gameThreads.length; i++) {
            if (i != 0) {
                timerManagers[i] = new TimerManager();
            }

            gameThreads[i] = new GameThread(timerManagers[i]);
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

            hitboxTracker = new HitboxTracker(this);
            this.addObject(hitboxTracker, 9);
        }
    }

    /**
     * start the game
     */
    public void run() {
        if (getMainThread().isRunning()) {
            return;
        }

        double startTime = System.currentTimeMillis();
        while (!(
                ReadyChecker.getStatBoolean(ReadyChecker.OUTPUT_READY) &&
                        ReadyChecker.getStatBoolean(ReadyChecker.RENDER_READY) &&
                        ReadyChecker.getStatBoolean(ReadyChecker.UPDATE_READY) &&
                        ReadyChecker.getStatBoolean(ReadyChecker.CAMERA_READY)
        )) {
            if ((System.currentTimeMillis() - startTime) > (loadingTimeOut * 1000)) {
                throw new TimeOutException("Time out, loading should finish in " + loadingTimeOut + " second.");
            }
        }

        for (GameThread thread : gameThreads) {
            thread.start();
        }

        if (debug) {
            this.debugPanel.start();
        }

        this.secondLoading = new SecondLoading(this);
        this.loading = false;
        System.gc();
        this.addObject(secondLoading, 0);
        this.second_loading = true;
    }

    /**
     * add an object to the list, start rendering and updating.
     *
     * @param gameObject the object which is going to be added
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
                this.objectsToBeAdded.get(priority).add(gameObject);
                break;
            default:
                throw new PriorityException("Property should between 0 and 9, but it is " + priority + ".");
        }
    }

    public void removeObject(GameObject gameObject, int priority) throws PriorityException {
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
                this.objectsToBeRemoved.get(priority).add(gameObject);
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

    public void removeTimer(Timer timer, int priority) throws PriorityException {
        if (priority < timerManagers.length) {
            this.timerManagers[priority].removeTimer(timer);
        } else {
            throw new PriorityException("Property should between 0 and 9, but it is " + priority + ".");
        }
    }

    /**
     * get the list of the objects
     *
     * @return the list of the objects
     */
    public ArrayList<ArrayList<GameObject>> getObjects() {
        return objects;
    }

    /**
     * get the main game thread (the thread with render and update) of this game
     *
     * @return the main thread of this game object
     */
    public GameThread getMainThread() {
        return gameThreads[0];
    }

    /**
     * check if the debug mode is on
     *
     * @return debug mode
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * get the debug panel
     *
     * @return the debug panel, return null if the debug mode is off
     */
    public DebugPanel getDebugPanel() {
        return debugPanel;
    }

    /**
     * get the camera
     *
     * @return the camera in use
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * change the using camera
     *
     * @param camera the new camera
     */
    public void setCamera(Camera camera) {
        this.camera = (camera == null) ? new NonCamera(this.output.getSize()) : camera;
    }

    /**
     * [auto call] remove objects which was made to be added in {@code addObject()}
     */
    public void cleanToBeAddedList() {
        for (int i = 0; i < objectsToBeAdded.size(); i++) {
            if (!objectsToBeAdded.get(i).isEmpty()) {
                int finalI = i;
                synchronized (objectsToBeAdded.get(i)) {
                    objectsToBeAdded.get(i).forEach(gameObject -> objects.get(finalI).add(gameObject));
                    objectsToBeAdded.set(i, new ArrayList<>());
                }
                System.gc();
            }
        }
    }

    /**
     * [auto call] remove object which was made to be removed in {@code removeObject()}
     */
    public void cleanObjects() {
        for (int i = 0; i < objectsToBeRemoved.size(); i++) {
            if (!objectsToBeRemoved.get(i).isEmpty()) {
                int finalI = i;
                synchronized (objectsToBeRemoved.get(i)) {
                    objectsToBeRemoved.get(i).forEach(gameObject -> objects.get(finalI).remove(gameObject));
                    objectsToBeRemoved.set(i, new ArrayList<>());
                }
                System.gc();
            }
        }
    }

    public HitboxTracker getHitboxTracker() {
        return this.hitboxTracker;
    }
}
