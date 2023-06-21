package jGame.loop.timer;

public abstract class Timer {
    private final double UPDATE_RATE;
    private double lastUpdate;
    private double accumulator = 0d;
    private double accumulatorUps = 0d;

    private int updateTime = 0;
    private int ups = 0;


    public Timer(double updateRateSecond) {
        this.UPDATE_RATE = updateRateSecond;
        lastUpdate = System.currentTimeMillis();
    }

    public void update() {
        double currentTimeMillis = System.currentTimeMillis();
        accumulator += currentTimeMillis - lastUpdate;
        accumulatorUps += currentTimeMillis - lastUpdate;
        lastUpdate = currentTimeMillis;
        if (accumulator > UPDATE_RATE * 1000d) {
            action();
            updateTime += 1;
            accumulator -= UPDATE_RATE * 1000d;
        }
        if (accumulatorUps > 1000d) {
            ups = updateTime;
            updateTime = 0;
            accumulatorUps -= 1000d;
        }
    }

    public int getUps() {
        return ups;
    }

    public abstract void action();
}
