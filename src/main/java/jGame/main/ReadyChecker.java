package jGame.main;

import jGame.exception.StatException;

/**
 * check if all items are ready
 */
public class ReadyChecker {
    public static final int OUTPUT_READY = 0;
    private static volatile boolean outputReady = false;

    public static final int RENDER_READY = 1;
    private static volatile boolean renderReady = false;

    public static final int UPDATE_READY = 2;
    private static volatile boolean updateReady = false;

    public static final int CAMERA_READY = 3;
    private static volatile boolean cameraReady = false;

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
                updateReady = stat;
                break;
            case CAMERA_READY:
                cameraReady = stat;
                break;
            default:
                throw new StatException("Cannot find the stat in boolean with the code " + statCode);
        }
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
            case UPDATE_READY:
                return updateReady;
            case CAMERA_READY:
                return cameraReady;
            default:
                throw new StatException("Cannot find the stat in boolean with the code " + statCode);
        }
    }
}
