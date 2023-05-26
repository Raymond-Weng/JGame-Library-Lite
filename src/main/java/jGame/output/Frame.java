package jGame.output;

import jGame.core.Size;

import javax.swing.*;
import java.awt.*;

public class Frame implements Output {
    JFrame jFrame;

    public Frame(Size size) {
        jFrame = new JFrame();
        jFrame.setVisible(false);
        jFrame.setSize(size.getIntWidth(), size.getIntHeight());
    }

    @Override
    public Graphics getGraphics() {
        return jFrame.getGraphics();
    }

    public void show(){
        jFrame.setVisible(true);
    }

    //TODO Frame
    //TODO builder for building an object
}
