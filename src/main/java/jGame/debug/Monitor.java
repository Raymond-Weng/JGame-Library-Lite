package jGame.debug;

import jGame.input.MouseListenerImpl;
import jGame.main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Monitor implements Runnable {
    private Game game;
    private JFrame jFrame;
    private Canvas canvas;
    private MouseListenerImpl mouseListener;

    public Monitor(Game game){
        this.game = game;
        this.jFrame = new JFrame();
        this.canvas = new Canvas();
        this.mouseListener = new MouseListenerImpl();

        jFrame.setVisible(false);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setTitle("Debug monitor");

        canvas.setFocusable(false);
        canvas.setSize(game.getOutput().getSize().toDimension());
        jFrame.getContentPane().add(canvas);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);

        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseListener);

        canvas.createBufferStrategy(3);

        jFrame.setVisible(true);
    }

    @Override
    public void run() {
        while (game.getMainThread().isRunning()){
            //TODO
        }
    }
}
