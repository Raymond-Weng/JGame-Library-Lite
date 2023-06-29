package jGame.debug;

import jGame.Game;

public interface DebugStringHandler {
    /**
     * tell debug panel what to show
     *
     * @param game the game object to get some information, the current game
     * @return the string show on the panel
     */
    String getText(Game game);
}
