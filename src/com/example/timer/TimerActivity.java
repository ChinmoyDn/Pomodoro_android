package com.example.timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TimePicker;

public class TimerActivity extends Activity implements OnClickListener {

	public static String TAG = "TimerActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer);
		TimePicker picker = (TimePicker) findViewById(R.id.picker);
		picker.setIs24HourView(true);
		picker.setCurrentHour(00);
		picker.setCurrentMinute(00);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.timer, menu);
//		return true;
//	}


	@Override
	public void onClick(View arg0) {
		EditText activityName = (EditText) findViewById(R.id.activity_name);
//		EditText time = (EditText) findViewById(R.id.time);
		TimePicker picker = (TimePicker) findViewById(R.id.picker);
		
		((Pomodoro)getApplication()).setActivityName(activityName.getText().toString());
//		((Pomodoro)getApplication()).setTime(time.getText().toString());
		((Pomodoro)getApplication()).setHours(picker.getCurrentHour());
		((Pomodoro)getApplication()).setMinutes(picker.getCurrentMinute());
		
		Log.d(TAG," activity name : " + activityName.getText().toString());
		
		Intent intent = new Intent(this, ActivityDetails.class);
		
		startActivity(intent);
	}
	
//			public void calculatePomodoros(int totalMinutes) {
//			minutesPassed = 0;
//			Log.d(TAG, "totalMinutes: " + totalMinutes);
//			while (minutesPassed < totalMinutes) {
//				Log.d(TAG, "timerFlag: " + timerFlag);
//				if (isStagePomodoro) {
//					timer(Pomodoro.POMODORO_TIME);
//					Log.d(TAG, "Stage = Pomodoro");
//					numOfPomo++;
//					isStagePomodoro = false;
//				} else if (br <= 1) {
//					Log.d(TAG, "Stage = Small Break");
//					timer(Pomodoro.SMALL_BREAK_TIME);
//					br++;
//					totalBr++;
//					isStagePomodoro = true;
//				} else {
//					Log.d(TAG, "Stage = Long Break");
//					timer(Pomodoro.LONG_BREAK_TIME);
//					br = 0;
//					longBr++;
//					isStagePomodoro = true;
//				}
//			}
//		}

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
	
}
