package eu.tomaka.anthrmonitor;

import android.util.Log;

import java.text.DateFormat;

public abstract class Watcher {

    protected long timeStarted, timeStopped;
    private String timeElapsedFormatted = "";
    private boolean running = false;
    private boolean wasEverStarted = false;

    protected MainActivity myact = null;

    public Watcher(MainActivity a) {
        myact = a;
    }

    public Watcher() {
        running = false;
    }

    public void start() {
        running = true;
        wasEverStarted = true;
        timeStarted = System.currentTimeMillis();
        timeElapsedFormatted = "running...";
    }

    private void calculateTimeElapsedFormatted() {
        long elapsedMillis = timeStopped - timeStarted;
        long elapsedSecs = elapsedMillis / 1000;
        long elapsedMins = elapsedSecs / 60;
        long elapsedHours = elapsedMins / 60;
        timeElapsedFormatted = elapsedHours + ":" + elapsedMins + ":"
                + elapsedSecs;
    }

    public void stop() {
        running = false;
        timeStopped = System.currentTimeMillis();
        calculateTimeElapsedFormatted();
    }

    public boolean isRunning() {
        return running;
    }

    public String getTimeStarted() {
        final String timeFormatted;
        if (wasEverStarted) {
            timeFormatted = DateFormat.getDateTimeInstance()
                    .format(timeStarted);
        }
        else {
            Log.e("Watcher","error in getDateTimeInstance");
            return null;
        }
        return timeFormatted;
    }

    private boolean wasStoppedSinceLastStart() {
        return wasEverStarted && !isRunning();
    }

    public String getTimeStopped(){
        final String timeFormatted;
        if (wasStoppedSinceLastStart()) {
            timeFormatted = DateFormat.getDateTimeInstance()
                    .format(timeStopped);
        }
        else {
            Log.e("Watcher","error in wasStoppedSinceLastStart");
            return null;
        }
        return timeFormatted;
    }

    public String getTimeElapsed() {
        return timeElapsedFormatted;
    }

}
