package jGame.loop.timer;

import jGame.loop.render.Render;
import jGame.loop.update.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;

/**
 * the manager of timers in a game thread
 *
 * @see Timer
 */
public class TimerManager {
    private final Render render;
    private final Update update;
    private final ArrayList<Timer> timers;
    private final ArrayList<Timer> timersToBeAdded;
    private final ArrayList<Timer> timersToBeRemoved;
    private volatile boolean pendingChanges = false;
    private volatile Thread thread = null;

    /**
     * create a timerManager with no render and update
     */
    public TimerManager() {
        this(null, null);
    }

    /**
     * create a timerManager with Render and Update
     *
     * @param render the Render of the game
     * @param update the Update of the game
     */
    public TimerManager(Render render, Update update) {
        this.render = render;
        this.update = update;
        timers = new ArrayList<>();
        timersToBeAdded = new ArrayList<>();
        timersToBeRemoved = new ArrayList<>();
    }

    /**
     * add a timer to the thread, and the timer will be called if the thread have time
     *
     * @param timer the timer to be added to the thread
     */
    public void addTimer(Timer timer) {
        synchronized (timersToBeAdded) {
            timersToBeAdded.add(timer);
            pendingChanges = true;
        }
        wakeUpThread();
    }

    /**
     * remove a timer from the thread, and it will not be called from that thread
     *
     * @param timer the timer to be removed from the thread
     */
    public void removeTimer(Timer timer) {
        synchronized (timersToBeRemoved) {
            timersToBeRemoved.add(timer);
            pendingChanges = true;
        }
        wakeUpThread();
    }

    /**
     * [auto call] set the thread running the timers, so it can be woken up when the timers are changed
     *
     * @param thread the thread running the timers
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     * check if there are timers waiting to be added or removed
     *
     * @return if there are timers waiting to be added or removed
     */
    public boolean hasPendingChanges() {
        return pendingChanges;
    }

    private void wakeUpThread() {
        Thread thread = this.thread;
        if (thread != null) {
            LockSupport.unpark(thread);
        }
    }

    /**
     * get the timer list of this thread
     *
     * @return the timer list
     */
    public ArrayList<Timer> getTimers() {
        return timers;
    }

    /**
     * get the timer list with update and render in front of them
     *
     * @return the full list
     */
    public ArrayList<Timer> getFullList() {
        ArrayList<Timer> list = new ArrayList<>(Arrays.asList(update, render));
        list.addAll(timers);
        return list;
    }

    /**
     * get the render of this thread
     *
     * @return the render of this thread
     */
    public Render getRender() {
        return render;
    }

    /**
     * get the update of this thread
     *
     * @return the update of this thread
     */
    public Update getUpdate() {
        return update;
    }

    /**
     * [auto call] add timers which was made to be added in {@code addTimer()}
     */
    public void cleanToBeAddedList() {
        synchronized (timersToBeAdded) {
            synchronized (timersToBeRemoved) {
                pendingChanges = false;
            }
            timers.addAll(timersToBeAdded);
            timersToBeAdded.clear();
        }
    }

    /**
     * [auto call] remove timers which was made to be removed in {@code removeTimer()}
     */
    public void cleanTimer() {
        synchronized (timersToBeRemoved) {
            timers.removeAll(timersToBeRemoved);
            timersToBeRemoved.clear();
        }
    }
}
