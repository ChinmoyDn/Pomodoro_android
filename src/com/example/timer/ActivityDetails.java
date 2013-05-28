package com.example.timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityDetails extends Activity implements OnClickListener {
    public static String TAG = "ActivityDetails";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int hr = ((Pomodoro) getApplication()).getHours();
        int mins = ((Pomodoro) getApplication()).getMinutes();
        ((Pomodoro)getApplication()).calculatePomodoros(hr*60+mins);
        Log.d(TAG, "on Resume Called");
        Log.d(TAG, "Activity Name: " + ((Pomodoro) getApplication()).getActivityName() + " Time" + hr + " : " + mins);
        updateUI();
    }

    @Override
    public void onClick(View v) {

        startActivity(new Intent(this, PomodoroActivity.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*public void calculatePomodoros(int totalMinutes) {

        Log.d(TAG, "totalMinutes: " + totalMinutes);
        while (minutesPassed < totalMinutes) {
//            Log.d(TAG, "timerFlag: " + timerFlag);
            if (isStagePomodoro) {
//                timer(Pomodoro.POMODORO_TIME);
                Log.d(TAG, "Stage = Pomodoro");
                numOfPomo++;
                isStagePomodoro = false;
				minutesPassed += Pomodoro.POMODORO_TIME;
            } else if (br <= 1) {
                Log.d(TAG, "Stage = Small Break");
//                timer(Pomodoro.SMALL_BREAK_TIME);
                br++;
                totalBr++;
				minutesPassed += Pomodoro.SMALL_BREAK_TIME;
                isStagePomodoro = true;
            } else {
                Log.d(TAG, "Stage = Long Break");
//              timer(Pomodoro.LONG_BREAK_TIME);
                br = 0;
                longBr++;
				minutesPassed += Pomodoro.LONG_BREAK_TIME;
                isStagePomodoro = true;
            }
        }
        updateUI();
    }*/

    public void updateUI(){

        TextView pom;
        TextView smalBrks;
        TextView longBrks;
        int br=0;
        int longBr=0;
        int numOfPomo=0;
        numOfPomo = ((Pomodoro) getApplication()).getCalculatedNumOfPomo();
        br =  ((Pomodoro) getApplication()).getTotalBr();
        longBr = ((Pomodoro) getApplication()).getLongBr();
        LinearLayout imageHolder= (LinearLayout) findViewById(R.id.pomodoro_img_holder);
        smalBrks = (TextView) findViewById(R.id.activity_dtls_no_of_small_brks);
        pom = (TextView) findViewById(R.id.activity_dtls_no_of_pomodoros);
        longBrks = (TextView) findViewById(R.id.avtivity_dtls_long_brks);

        pom.setText(String.valueOf(numOfPomo));
        smalBrks.setText(String.valueOf(br));
        longBrks.setText(String.valueOf(longBr));
        imageHolder.setGravity(0x11);
        for(int i=0; i <numOfPomo; i++){
            ImageView image = new ImageView(ActivityDetails.this);
            image.setBackgroundResource(R.drawable.pomodoro_icon);
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            image.setMinimumWidth(52);
            image.setMaxHeight(48);
            image.setMaxWidth(48);
            imageHolder.addView(image, -1, new ViewGroup.LayoutParams(68,48));
        }



    }

}
