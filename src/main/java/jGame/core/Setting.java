/**
 * The setting for game, please compile all for the method of this.
 */

package jGame.core;

import jGame.loop.Render;
import jGame.loop.Update;
import jGame.output.Output;

public interface Setting {
    public Output getOutput();

    public Render getRender();

    public Update getUpdate();


    //TODO other settings
}
