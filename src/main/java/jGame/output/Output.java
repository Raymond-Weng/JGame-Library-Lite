package jGame.output;

import jGame.core.Size;

import java.awt.*;

/**
 * The output(frame, web, etc.&nbsp;) of the game.
 * PLEASE handle the stat OUTPUT_READY
 *
 * @see jGame.main.ReadyChecker
 */
public interface Output {

    /**
     * get the Graphic object to allow game draw on the screen
     *
     * @return Graphic object of the display
     */
    Graphics getGraphics();

    /**
     * show the things draw on the Graphic
     */
    void show();

    /**
     * return the size of the game
     * @return the size of the game
     */
    Size getSize();


    //TODO output methods
}
