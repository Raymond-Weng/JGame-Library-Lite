package jGame.debug;

import jGame.exception.StatException;

public class Stat {
    public static final int OUTPUT_READY = 0;
    private static volatile boolean outputReady = false;

    public static final int RENDER_READY = 1;
    private static volatile boolean renderReady = false;

    public static final int UPDATE_READY = 2;
    private static volatile boolean updateReady = false;

    /**
     * set the stat (this method work in only stats in boolean)
     *
     * @param statCode the code of the stat, please use the constants in this class
     * @param stat     the new stat of the item to be edited
     */
    public static void setStatBoolean(int statCode, boolean stat) {
        switch (statCode) {
            case OUTPUT_READY:
                outputReady = stat;
                break;
            case RENDER_READY:
                renderReady = stat;
                break;
            case UPDATE_READY:

            default:
                throw new StatException("Cannot find the stat in boolean with the code " + statCode);
        }
    }

    public static void setStatInteger(int statCode, int stat) {

    }

    /**
     * get the stat (this method work in only stats in boolean)
     *
     * @param statCode the code of the stat, please use the constants in this class
     * @return the stat now
     */
    public static boolean getStatBoolean(int statCode) {
        switch (statCode) {
            case OUTPUT_READY:
                return outputReady;
            case RENDER_READY:
                return renderReady;
            default:
                throw new StatException("Cannot find the stat in boolean with the code " + statCode);
        }
    }
}
