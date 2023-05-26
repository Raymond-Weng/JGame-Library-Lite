package jGame.output;

import jGame.core.Size;

import javax.swing.*;
import java.awt.*;

public class Frame implements Output {
    public static class Builder {
        private Size size = null;

        public Builder setSize(Size size) {
            this.size = size;
            return this;
        }

        private int numBufferStrategy;
        public Builder setNumBufferStrategy(int numBufferStrategy) {
            this.numBufferStrategy = numBufferStrategy;
            return this;
        }

        public Frame build() {
            return new Frame(size,
                    numBufferStrategy);
        }
    }

    private JFrame jFrame;
    private Canvas canvas;
    private Size size;
    private int numBufferStrategy;

    public Frame(Size size, int numBufferStrategy) {
        this.size = size;
        this.numBufferStrategy = numBufferStrategy;

        jFrame = new JFrame();
        canvas = new Canvas();

        jFrame.setVisible(false);
        jFrame.setSize(size.getIntWidth(), size.getIntHeight());
        jFrame.add(canvas);
    }

    @Override
    public Graphics getGraphics() {
        return canvas.getBufferStrategy().getDrawGraphics();
    }

    public void show() {
        jFrame.setVisible(true);
        canvas.createBufferStrategy(numBufferStrategy);
        Graphics graphics = this.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, size.getIntWidth(), size.getIntHeight());
    }

    //TODO Frame
    //TODO builder for building an object
}
