package jGame.output;

import jGame.core.Size;
import jGame.exception.BuilderException;
import jGame.main.Game;
import jGame.main.ReadyChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * the basic display of a game
 */
public class Frame implements Output {
    public static class Builder {
        private Size size = null;

        /**
         * [must contain]the size of the display area
         *
         * @param size the size of the display area
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setSize(Size size) {
            this.size = size;
            return this;
        }

        private int numBufferStrategy = -1;

        /**
         * the number of the bufferStrategy
         *
         * @param numBufferStrategy how many bufferStrategy should this frane have
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         * @see java.awt.image.BufferStrategy
         */
        public Builder setNumBufferStrategy(int numBufferStrategy) {
            this.numBufferStrategy = numBufferStrategy;
            return this;
        }

        private int defaultCloseOperation = -1;

        /**
         * set the thing to do if the red "x" is clicked
         *
         * @param defaultCloseOperation the code defined in {@code JFrame}
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         * @see JFrame
         */
        public Builder setDefaultCloseOperation(int defaultCloseOperation) {
            this.defaultCloseOperation = defaultCloseOperation;
            return this;
        }

        private String frameTitle = null;

        /**
         * set the title of this frame
         *
         * @param title the title of this
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setFrameTitle(String title) {
            this.frameTitle = title;
            return this;
        }

        private Image icon = null;

        /**
         * set the icon of the frame
         *
         * @param icon the icon of the frame
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setIcon(Image icon) {
            this.icon = icon;
            return this;
        }

        private MouseListener mouseListener = null;

        /**
         * set the mouse input
         *
         * @param mouseListener the mouse input to be added to frame
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         * @see jGame.input.MouseListenerImpl
         */
        public Builder setMouseListener(MouseListener mouseListener) {
            this.mouseListener = mouseListener;
            return this;
        }

        private KeyListener keyListener;

        /**
         * set the key input
         *
         * @param keyListener the key input to be added to frame
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         * @see jGame.input.KeyListenerImpl
         */
        public Builder setKeyListener(KeyListener keyListener) {
            this.keyListener = keyListener;
            return this;
        }

        private MouseMotionListener mouseMotionListener;

        /**
         * set the mouse motion input
         *
         * @param mouseMotionListener the mouse motion input to be added to the frame
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setMouseMotionListener(MouseMotionListener mouseMotionListener) {
            this.mouseMotionListener = mouseMotionListener;
            return this;
        }

        private boolean fullScreen = false;

        /**
         * set if the frame should be full screen
         *
         * @param fullScreen if the frame should be full screen
         * @return this builder, then you can connect {@code .setXXX(XXX)} right after this method
         */
        public Builder setFullScreen(boolean fullScreen) {
            this.fullScreen = fullScreen;
            return this;
        }

        /**
         * build to get the frame object
         *
         * @return the object created with arguments
         */
        public Frame build() {
            if (size != null)
                return new Frame(
                        size,
                        (numBufferStrategy == -1) ? 2 : numBufferStrategy,
                        (defaultCloseOperation == -1) ? JFrame.EXIT_ON_CLOSE : defaultCloseOperation,
                        (frameTitle == null) ? "Game" : frameTitle,
                        icon,
                        mouseListener,
                        mouseMotionListener,
                        keyListener,
                        fullScreen
                );
            else
                throw new BuilderException("There is some missing args.");
        }
    }

    private final JFrame jFrame;
    private final Canvas canvas;
    private final Size size;
    private volatile Game game;

    private Frame(Size size,
                  int numBufferStrategy,
                  int defaultCloseOperation,
                  String frameTitle,
                  Image icon,
                  MouseListener mouseListener,
                  MouseMotionListener mouseMotionListener,
                  KeyListener keyListener,
                  boolean fullScreen
    ) {
        this.size = fullScreen ? new Size(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height) : size;

        jFrame = new JFrame();
        canvas = new Canvas();

        if (fullScreen) {
            jFrame.setUndecorated(true);
        }
        jFrame.setVisible(false);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(defaultCloseOperation);
        jFrame.setTitle(frameTitle);
        jFrame.setIconImage(icon);

        canvas.setFocusable(false);
        canvas.setSize(size.toDimension());
        jFrame.getContentPane().add(canvas);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);

        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseMotionListener);
        jFrame.addKeyListener(keyListener);

        canvas.createBufferStrategy(numBufferStrategy);

        if (fullScreen) {
            GraphicsEnvironment.
                    getLocalGraphicsEnvironment().
                    getDefaultScreenDevice().
                    setFullScreenWindow(jFrame);
        }
    }


    /**
     * give game to this frame to let it know more information
     *
     * @param game the current game object
     */
    public void setGame(Game game) {
        this.game = game;
        ReadyChecker.setStatBoolean(ReadyChecker.OUTPUT_READY, true);
    }

    public void showFrame() {
        if (game == null) {
            throw new NullPointerException("The game object hasn't been set yet.");
        }

        jFrame.setVisible(true);

        new GameLaunching(game).start();
    }


    @Override
    public Graphics getGraphics() {
        return canvas.getBufferStrategy().getDrawGraphics();
    }

    @Override
    public void show() {
        canvas.getBufferStrategy().show();
    }

    @Override
    public Size getSize() {
        return this.size;
    }
}
