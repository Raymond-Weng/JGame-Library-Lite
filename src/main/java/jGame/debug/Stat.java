package jGame.debug;

public class Stat {
    public static final int RENDER_READY = 0;
    private static volatile boolean renderReady = false;


    public static void setStatBoolean(int statCode, boolean stat){
        switch (statCode){
            case RENDER_READY:
                renderReady = stat;
                break;
        }
    }

    public static void setStatInteger(int statCode, int stat){

    }

    public static boolean getStatBoolean(int statCode){
        switch (statCode){
            case RENDER_READY:
                return renderReady;
            default:
                return false;
        }
    }
}
