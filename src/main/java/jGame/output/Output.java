/**
 * The output(frame, web, etc.&nbsp;) of the game.
 * PLEASE handle the stat OUTPUT_READY
 */

package jGame.output;

import jGame.core.Size;

import java.awt.*;

public interface Output {

    /**
     * get the Graphic object to allow game draw on the screen
     *
     * @return Graphic object of the display
     */
    public Graphics getGraphics();

    /**
     * show the things draw on the Graphic
     */
    public void show();

    /**
     * return the size of the game
     * @return the size of the game
     */
    public Size getSize();

    //TODO output methods
}
