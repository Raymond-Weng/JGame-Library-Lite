package jGame.loop.timer;

/**
 * everything you need to run in the loop, extend this to let it work
 *
 * @see TimerManager
 */
public abstract class Timer {
    public final double UPDATE_RATE;
    private double accumulator = 0d;
    private double accumulatorUps = 0d;

    /**
     * this is the update time of the timer, minus this by 1 if you do nothing because of some checking item after {@code update()} was called
     */
    protected volatile int updateTime = 0;
    private volatile int ups = 0;
    private final int maxUps;

    /**
     * create a timer
     *
     * @param maxUps           set the max update per second, this will not be updated when the update time is bigger than this even if it's time to update (skip this argument or set this to {@code -1} to ignore the max ups)
     * @param updateRateSecond the minimum time between two update
     */
    public Timer(int maxUps, double updateRateSecond) {
        this.UPDATE_RATE = updateRateSecond;
        this.maxUps = maxUps;
    }

    /**
     * create a timer with no max update per second
     *
     * @param updateRateSecond the minimum time between two update
     */
    public Timer(double updateRateSecond) {
        this(-1, updateRateSecond);
    }

    /**
     * the thread will call this and the method will compute if this can be updated
     *
     * @param timePassed time passed since last time the thread call this function
     */
    public void update(double timePassed) {
        accumulator += timePassed;
        accumulatorUps += timePassed;
        if (accumulator > UPDATE_RATE * 1000d) {
            if (maxUps == -1 || maxUps > ups) {
                action();
                updateTime += 1;
                accumulator -= UPDATE_RATE * 1000d;
            }
        }
        if (accumulatorUps > 1000d) {
            ups = updateTime;
            updateTime = 0;
            accumulatorUps -= 1000d;
        }
    }

    /**
     * get the update per second of this timer
     *
     * @return update per second of this timer
     */
    public int getUps() {
        return ups;
    }

    /**
     * if it's time to update, this method will be called
     */
    public abstract void action();
}
