/**
 * The main object to control whole game.
 */

package jGame;

import jGame.core.Setting;
import jGame.exception.PriorityException;
import jGame.gameObject.GameObject;

import java.util.ArrayList;

public class Game {
    private Setting setting;
    private ArrayList<ArrayList<GameObject>> objects;


    /**
     * The creating of the game.
     *
     * @param setting the setting of the game, pass the object after finishing the class.
     */
    public Game(Setting setting) {
        this.setting = setting;
        objects = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            objects.add(new ArrayList<>());
        }
    }

    /**
     * build the game, like loading output, reading file, etc.
     */
    public void build() {

    }

    /**
     * start the game
     */
    public void run() {
        //TODO game start method
    }

    /**
     * get the setting of the game
     *
     * @return the setting of the game
     */
    public Setting getSetting() {
        return setting;
    }

    /**
     * add an object to the list, start rendering and updating.
     *
     * @param gameObject the object which is going to be added
     * @param priority   the render (or update) priority, it will be rendered (or updated) quicker if it bigger. (This should be between 0 and 9)
     * @throws PriorityException if the priority is not between 0 and 9, it will throw an exception.
     */
    public void addObject(GameObject gameObject, int priority) throws PriorityException {
        switch (priority) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                this.objects.get(priority).add(gameObject);
                break;
            default:
                throw new PriorityException("Property should between 0 and 9, but it is " + priority + ".");
        }
    }
}
