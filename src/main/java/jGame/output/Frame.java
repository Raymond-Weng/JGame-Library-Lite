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
        return null;
    }

    //TODO Frame
}
