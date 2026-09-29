package jGame.loop.timer;

import jGame.loop.render.Render;
import jGame.loop.update.Update;

import java.util.concurrent.locks.LockSupport;

/**
 * threads running in the game, if you have some timer need to run in different thread of render and output, use this in the {@code Game} class
 *
 * @see TimerManager
 */
public class GameThread extends Thread {
    /**
     * sleeping is not accurate (it oversleeps), so the last part of the wait is spun instead
     */
    private static final long SPIN_THRESHOLD_NANOS = 1_000_000L;

    /**
     * the longest time to sleep when there is nothing to do, the thread will be woken up early if a timer is added
     */
    private static final long MAX_WAIT_NANOS = 100_000_000L;

    private final TimerManager timerManager;

    private volatile boolean running = false;

    long lastUpdate = -1;

    /**
     * create a new thread ({@code Game} class will handle this)
     *
     * @param timerManager the timer manager, which let this know what timer need to run
     */
    public GameThread(TimerManager timerManager) {
        this.timerManager = timerManager;
    }

    @Override
    public synchronized void start() {
        running = true;
        timerManager.setThread(this);
        super.start();
    }

    @Override
    public void run() {
        lastUpdate = System.nanoTime();
        while (running) {
            long currentTime = System.nanoTime();
            double timePassed = (currentTime - lastUpdate) / 1_000_000d;
            lastUpdate = currentTime;

            double timeUntilNextUpdate;
            synchronized (timerManager.getTimers()) {
                Update update = timerManager.getUpdate();
                Render render = timerManager.getRender();
                boolean withUpdateAndRender = update != null && render != null;
                if (withUpdateAndRender) {
                    update.update(timePassed);
                    render.update(timePassed);
                }
                timerManager.getTimers().forEach(timer -> timer.update(timePassed));
                timerManager.cleanToBeAddedList();
                timerManager.cleanTimer();

                timeUntilNextUpdate = Double.MAX_VALUE;
                if (withUpdateAndRender) {
                    timeUntilNextUpdate = Math.min(update.getTimeUntilNextUpdate(), render.getTimeUntilNextUpdate());
                }
                for (Timer timer : timerManager.getTimers()) {
                    timeUntilNextUpdate = Math.min(timeUntilNextUpdate, timer.getTimeUntilNextUpdate());
                }
            }

            if (timeUntilNextUpdate > 0) {
                long waitNanos = (long) Math.min(timeUntilNextUpdate * 1_000_000d, MAX_WAIT_NANOS);
                waitUntil(currentTime + waitNanos);
            }
        }
    }

    /**
     * sleep until the deadline (sleep for most of the time, then spin for the last part to be accurate),
     * returns early if the thread is stopped or the timers are changed
     *
     * @param deadline the time to wake up, in {@code System.nanoTime()}
     */
    private void waitUntil(long deadline) {
        long remaining;
        while (running && !timerManager.hasPendingChanges()
                && (remaining = deadline - System.nanoTime()) > SPIN_THRESHOLD_NANOS) {
            // the oversleep grows with the sleep time (about 25% on macOS), so only sleep half of the time left each round,
            // it gets close to the deadline in a few rounds without passing it
            LockSupport.parkNanos(this, (remaining - SPIN_THRESHOLD_NANOS) / 2);
        }
        while (running && !timerManager.hasPendingChanges() && System.nanoTime() < deadline) {
            Thread.onSpinWait();
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
        LockSupport.unpark(this);
    }
}
