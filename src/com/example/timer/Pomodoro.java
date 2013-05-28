package com.example.timer;

import android.app.Application;
import android.util.Log;

public class Pomodoro extends Application {

	public static final String TAG = "Pomodoro";
	public static final int POMODORO_TIME =25;
	public static final int SMALL_BREAK_TIME =5;
	public static final int LONG_BREAK_TIME =15;

	private String activityName = "";
	 String time = "";
	 private int hours = 0, minutes = 0, totalBr = 0, longBr = 0, hr, min, totalMins = 0,
             minutesPassed = 0, calculatedNumOfPomo = 0, br = 0, numOfPomSet = 0;
     boolean isStagePomodoro = true;


    public int getCalculatedNumOfPomo() {
        return calculatedNumOfPomo;
    }

    public int getLongBr() {
        return longBr;
    }

    public int getTotalBr() {
        return totalBr;
    }

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate called");
	}

	//setters and getters
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getHours() {
		return hours;
	}
	
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

    public void calculatePomodoros(int totalMinutes) {

        Log.d(TAG, "totalMinutes: " + totalMinutes);
        while (minutesPassed < totalMinutes) {
//            Log.d(TAG, "timerFlag: " + timerFlag);
            if (isStagePomodoro) {
                Log.d(TAG, "Stage = Pomodoro");
                calculatedNumOfPomo++;
                isStagePomodoro = false;
                minutesPassed += Pomodoro.POMODORO_TIME;
            } else if (br <= 1) {
                Log.d(TAG, "Stage = Small Break");
                br++;
                totalBr++;
                minutesPassed += Pomodoro.SMALL_BREAK_TIME;
                isStagePomodoro = true;
            } else {
                Log.d(TAG, "Stage = Long Break");
                br = 0;
                longBr++;
                minutesPassed += Pomodoro.LONG_BREAK_TIME;
                isStagePomodoro = true;
            }
        }
    }

};
