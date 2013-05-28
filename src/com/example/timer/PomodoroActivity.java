package com.example.timer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PomodoroActivity extends Activity {

	public static String TAG = "PomodoroActivity";
	public static int COUNT_INTERVAL = 1000;
	private boolean timerFlag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pomodoro_activity_layout);
        timerFlag = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
        timerFlag = false;
	}

    public void callDoPomodoro(int hr, int mins){
        if(timerFlag)
            new DoPomodoro().execute(hr, mins);
    }

	@Override
	protected void onResume() {
		super.onResume();
		int hr = ((Pomodoro) getApplication()).getHours();
		int mins = ((Pomodoro) getApplication()).getMinutes();
		Log.d(TAG, "on Resume Called");
		Log.d(TAG,
				"Activity Name: "
						+ ((Pomodoro) getApplication()).getActivityName()
                        + " Time" + hr + " : " + mins);
        callDoPomodoro(hr,mins);
	}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerFlag=false;
    }

	private class DoPomodoro extends AsyncTask<Integer, Integer, Void> {


		TextView completed = (TextView) findViewById(R.id.completed);
		TextView stage = (TextView) findViewById(R.id.label_stage);
		TextView numPomodoros = (TextView) findViewById(R.id.pomodoros_completed);
		TextView smallBr = (TextView) findViewById(R.id.label_breaks);
		TextView longBrTextView = (TextView) findViewById(R.id.label_long_break);
		ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Integer... params) {
			totalMins = params[0] * 60 + params[1];
//            ((Pomodoro)getApplication()).calculatePomodoros(totalMins);
            executePomodoros(totalMins);
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			String stage_label = "Stage : "
					+ (isStagePomodoro ? "Pomodoro" : "Break");
			Log.d(TAG, String.format( "No of Pomodoro: %d Breaks: %d Long Breaks: ", numOfPomo,
					totalBr, longBr));

			Log.d(TAG, String.format("(%d / %d) * 100 = %f", minutesPassed,
					totalMins, ((float) minutesPassed / (float) totalMins) * 100));

			stage.setText(stage_label);
			numPomodoros.setText("Pomodoros Completed : "
					+ String.valueOf(numOfPomo));
			smallBr.setText("Total small Breaks : " + String.valueOf(totalBr));
			longBrTextView.setText("Total Long Breaks : " + String.valueOf(longBr));
			progress.setProgress((int) (((float) minutesPassed / (float) totalMins) * 100));
			completed.setText("Current "+stage_label+" : "+String.valueOf(values[0]+1));
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			completed.setText("Completed!!");
			Log.d(TAG, "Task Finished");
		}

		public void timer(int timeInMins) {
			Log.d(TAG, "timeInMis " + timeInMins);
			try {
				for (int i = 0; i < timeInMins; i++) {
					Thread.sleep(1000);
					minutesPassed++;
					publishProgress(i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

        private int numOfPomo = 0, br = 0, totalBr = 0, longBr = 0, hr, min,
                totalMins = 0, minutesPassed = 0;
        private boolean isStagePomodoro = true;

		public void executePomodoros(int totalMinutes) {
			minutesPassed = 0;
			Log.d(TAG, "totalMinutes: " + totalMinutes);
			while (numOfPomo < ((Pomodoro)getApplication()).getCalculatedNumOfPomo()) {
				if (isStagePomodoro) {
					timer(Pomodoro.POMODORO_TIME);
					Log.d(TAG, "Stage = Pomodoro");
					numOfPomo++;
					isStagePomodoro = false;
					minutesPassed += Pomodoro.POMODORO_TIME;
				} else if (br <= 1) {
					Log.d(TAG, "Stage = Small Break");
					timer(Pomodoro.SMALL_BREAK_TIME);
					br++;
                    totalBr++;
					minutesPassed += Pomodoro.SMALL_BREAK_TIME;
					isStagePomodoro = true;
				} else {
					Log.d(TAG, "Stage = Long Break");
					timer(Pomodoro.LONG_BREAK_TIME);
					br = 0;
					longBr++;
					minutesPassed += Pomodoro.LONG_BREAK_TIME;
					isStagePomodoro = true;
				}
			}
		}

	}



}
