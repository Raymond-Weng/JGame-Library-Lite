package jGame.loop.timer;

import com.sun.istack.internal.NotNull;

import jGame.loop.render.Render;
import jGame.loop.update.Update;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * the manager of timers in a game thread
 *
 * @see Timer
 */
public class TimerManager {
    private Render render;
    private Update update;
    private ArrayList<Timer> timers;

    /**
     * create a timerManager with no render and update
     */
    public TimerManager() {
        timers = new ArrayList<>();
    }

    /**
     * create a timerManager with Render and Update
     *
     * @param render the Render of the game
     * @param update the Update of the game
     */
    public TimerManager(@NotNull Render render, @NotNull Update update) {
        this.render = render;
        this.update = update;
        timers = new ArrayList<>();
    }

    /**
     * add a timer to the thread, and the timer will be called if the thread have time
     *
     * @param timer the timer to be added to the thread
     */
    public void addTimer(Timer timer) {
        timers.add(timer);
    }

    /**
     * remove a timer from the thread, and it will not be called from that thread
     *
     * @param timer the timer to be removed from the thread
     */
    public void removeTimer(Timer timer) {
        timers.remove(timer);
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
}
