package javalampstudos.kingofqueens.kingOfQueens.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javalampstudos.kingofqueens.MainActivity;

/**
 * Created by User on 02/05/2017.
 */

public class GameTimer  {
    public int minutes, seconds;
    public long millis, startTime, currentTime;
    private ScheduledExecutorService thread;
    private ScheduledFuture<?> scheduledFuture;


    public GameTimer() {
        minutes = 0;
        seconds = 0;
        millis = 0L;
        thread = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Starts or resumes the timer.
     */
    public void start() {
        GTimerTask timerTask = new GTimerTask();

        startTime = System.currentTimeMillis() - currentTime;

        scheduledFuture = thread.scheduleAtFixedRate(timerTask, 0, 1000,
                TimeUnit.MILLISECONDS);
    }

    /**
     * Stops or pauses the timer.
     */
    public void stop() {
        currentTime = millis;
        scheduledFuture.cancel(true);
    }

    private class GTimerTask implements Runnable {
        /**
         * Creates a new timer task.
         */
        public GTimerTask() {
        }

        public void run() {
            long diff = System.currentTimeMillis() - startTime - millis;
            millis = System.currentTimeMillis() - startTime;
            if(millis > MainActivity.setting.getInt("longestPlayTimeValue"))
                MainActivity.setting.setInt("longestPlayTimeValue",
                        (int) millis);
            MainActivity.setting.addToInt("totalPlayTimeValue", (int) diff);
            seconds = (int) millis / 1000;
            minutes = seconds / 60;
            seconds = seconds % 60;
        }
    }

//    /**
//     * Returns the current second value of the timer.
//     *
//     * @return The current second value.
//     */
//    public int getSeconds() {
//        return seconds;
//    }
//
//    /**
//     * Returns the current minute value of the timer.
//     *
//     * @return The current minute value.
//     */
//    public int getMinutes() {
//        return minutes;
//    }
//
//    /**
//     * Returns a score based on the current time of the timer.
//     *
//     * @return The score based on the current time.
//     */
//    public int getScore() {
//        return (int) (millis / 1000) * 10;
//    }

}


