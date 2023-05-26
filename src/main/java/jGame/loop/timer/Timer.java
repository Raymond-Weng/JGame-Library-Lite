package jGame.loop.timer;

public abstract class Timer {
    private final double updateRate;
    private double lastUpdate;
    private double accumulator = 0;
    private double lastUpdateUps;
    private double accumulatorUps = 0;

    private int updateTime = 0;
    private int ups = 0;


    public Timer(double updateRate) {
        this.updateRate = updateRate;
        lastUpdate = System.currentTimeMillis();
    }

    public void update() {
        double currentTimeMillis = System.currentTimeMillis();
        accumulator += currentTimeMillis - lastUpdate;
        lastUpdate = currentTimeMillis;
        if (accumulator > updateRate * 1000d) {
            action();
            updateTime += 1;
            accumulator -= updateRate * 1000d;
        }
        accumulatorUps += currentTimeMillis - lastUpdateUps;
        lastUpdateUps = currentTimeMillis;
        if (accumulatorUps > 1000d) {
            ups = updateTime;
            updateTime = 0;
            accumulator -= 1000d;
        }
    }

    public int getUps() {
        return ups;
    }

    public abstract void action();
}
