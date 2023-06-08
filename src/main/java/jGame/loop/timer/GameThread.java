package jGame.loop.timer;

public class GameThread extends Thread {
    private TimerManager timerManager;

    private boolean running = false;

    public GameThread(TimerManager timerManager) {
        this.timerManager = timerManager;
    }

    @Override
    public void run() {
        while (running) {
            if (timerManager.getUpdate() == null || timerManager.getRender() == null) {
                timerManager.getTimers().forEach(Timer::update);
            } else {
                timerManager.getFullList().forEach(Timer::update);
            }
        }
    }

    public TimerManager getTimerManager() {
        return timerManager;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopTimer() {
        running = false;
    }
}
