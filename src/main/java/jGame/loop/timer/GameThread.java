package jGame.loop.timer;

import java.util.ArrayList;

public class GameThread extends Thread {
    private ArrayList<Timer> arrayList;

    private boolean running = false;

    public GameThread(ArrayList<Timer> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public void run() {
        while(running){
            arrayList.forEach(Timer::update);
        }
    }

    public ArrayList<Timer> getArrayList() {
        return arrayList;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopTimer(){
        running = false;
    }
}
