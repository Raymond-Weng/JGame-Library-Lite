package jGame.loop.timer;

import jGame.loop.render.Render;
import jGame.loop.update.Update;

import java.util.ArrayList;
import java.util.Arrays;

public class TimerManager {
    private Render render;
    private Update update;
    private ArrayList<Timer> timers;

    public TimerManager(Render render, Update update) {
        this.render = render;
        this.update = update;
        timers = new ArrayList<>();
    }

    public void addTimer(Timer timer) {
        timers.add(timer);
    }

    public void removeTimer(Timer timer) {
        timers.remove(timer);
    }

    public ArrayList<Timer> getTimers() {
        return timers;
    }

    public ArrayList<Timer> getFullList() {
        ArrayList<Timer> list = new ArrayList<>(Arrays.asList(render, update));
        list.addAll(timers);
        return list;
    }
}
