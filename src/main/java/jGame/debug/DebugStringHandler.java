package jGame.debug;

import com.sun.istack.internal.NotNull;
import jGame.main.Game;

public interface DebugStringHandler {
    /**
     * tell debug panel what to show
     *
     * @param game the game object to get some information, the current game
     * @return the string show on the panel
     */
    String getText(@NotNull Game game);
}
