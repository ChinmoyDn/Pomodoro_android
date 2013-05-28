package com.example.timer;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by chinmoy on 25/5/13.
 */
//public class DoPomodoro {
//}

public class DoPomodoro {

    private static String TAG = "DoPomodoro";

    private int numOfPomo = 0, br = 0, totalBr = 0, longBr = 0, hr, min,
            totalMins = 0, minutesPassed = 0;
    private boolean isStagePomodoro = true;


    public void timer(int timeInMins) {
//        Log.d(TAG, "Thread: " + Thread.currentThread());
        try {
            for (int i = 0; i < timeInMins; i++) {
                Thread.sleep(1000);
                minutesPassed++;
//                publishProgress(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void calculatePomodoros(int totalMinutes) {

        minutesPassed = 0;
//        Log.d(TAG, "totalMinutes: " + totalMinutes);
        while (minutesPassed < totalMinutes) {
            if (isStagePomodoro) {
                timer(Pomodoro.POMODORO_TIME);
                Log.d(TAG, "Stage = Pomodoro");
                numOfPomo++;
                isStagePomodoro = false;
//					minutesPassed += Pomodoro.POMODORO_TIME;
            } else if (br <= 1) {
                Log.d(TAG, "Stage = Small Break");
                timer(Pomodoro.SMALL_BREAK_TIME);
                br++;
                totalBr++;
//					minutesPassed += Pomodoro.SMALL_BREAK_TIME;
                isStagePomodoro = true;
            } else {
                Log.d(TAG, "Stage = Long Break");
                timer(Pomodoro.LONG_BREAK_TIME);
                br = 0;
                longBr++;
//					minutesPassed += Pomodoro.LONG_BREAK_TIME;
                isStagePomodoro = true;
            }
        }
    }
}


