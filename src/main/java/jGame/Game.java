/**
    The main object to control whole game.
 */

package jGame;

import jGame.core.Setting;

public class Game {
    public Setting setting;

    /**
     The creating of the game.

     @param setting the setting of the game, pass the object after finishing the class.
     */
    public Game(Setting setting){
        this.setting = setting;
    }

    /**
     * build the game, like loading output, reading file, etc.
     */
    public void build(){
        //TODO building
    }

    /**
     * start the game
     */
    public void run() {
        //TODO game start method
    }
}
