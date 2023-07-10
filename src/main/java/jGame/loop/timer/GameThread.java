package jGame.loop.timer;

/**
 * threads running in the game, if you have some timer need to run in different thread of render and output, use this in the {@code Game} class
 *
 * @see TimerManager
 */
public class GameThread extends Thread {
    private TimerManager timerManager;

    private boolean running = false;

    /**
     * create a new thread ({@code Game} class will handle this)
     *
     * @param timerManager the timer manager, which let this know what timer need to run
     */
    public GameThread(TimerManager timerManager) {
        this.timerManager = timerManager;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            if (timerManager.getUpdate() == null || timerManager.getRender() == null) {
                timerManager.getTimers().forEach(Timer::update);
            } else {
                timerManager.getFullList().forEach(Timer::update);
            }
        }
    }

    /**
     * use this if you need the timerManager
     *
     * @return the timerManager of this
     */
    public TimerManager getTimerManager() {
        return timerManager;
    }

    /**
     * check if this thread is running
     *
     * @return is this thread running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * stop the thread
     */
    public void stopTimer() {
        running = false;
    }
}
