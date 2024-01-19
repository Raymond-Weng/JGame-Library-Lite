package jGame.loop.timer;

/**
 * threads running in the game, if you have some timer need to run in different thread of render and output, use this in the {@code Game} class
 *
 * @see TimerManager
 */
public class GameThread extends Thread {
    private final TimerManager timerManager;

    private volatile boolean running = false;

    double lastUpdate = -1;

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
        lastUpdate = System.currentTimeMillis();
        running = true;
        while (running) {
            double currentTime = System.currentTimeMillis();
            double timePassed = currentTime - lastUpdate;
            lastUpdate = currentTime;
            if (timerManager.getUpdate() == null || timerManager.getRender() == null) {
                synchronized (timerManager.getTimers()) {
                    timerManager.getTimers().forEach(timer -> timer.update(timePassed));
                    timerManager.cleanToBeAddedList();
                    timerManager.cleanTimer();
                }
            } else {
                synchronized (timerManager.getTimers()) {
                    timerManager.getFullList().forEach(timer -> timer.update(timePassed));
                    timerManager.cleanToBeAddedList();
                    timerManager.cleanTimer();
                }
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
