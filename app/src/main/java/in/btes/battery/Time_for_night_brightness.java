package in.btes.battery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

// Created by Rajat Sharma on 27-04-2018.



public class Time_for_night_brightness extends Activity implements View.OnClickListener {
    private TimePicker timePicker2;
    private TextView time2, time3;
    private Calendar calendar;
    private String format = "";
    Button from;
    Button to;
    Button OK;
    int hour, min;
    Date date;
    private AudioManager myAudioManager;
    String checkSlaMissedOrNot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_for_night_brightness);

        timePicker2 = (TimePicker) findViewById(R.id.timePicker);
        time2 = (TextView) findViewById(R.id.textView6);
        time3 = findViewById(R.id.textView7);
        from = findViewById(R.id.button4);
        to = findViewById(R.id.button5);
        OK = findViewById(R.id.button6);
        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //Date CurrentTime = dateFormat.parse(dateFormat.format(new Date()));

        date = new Date();


        calendar = Calendar.getInstance();


        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);


        from.setOnClickListener(this);
        to.setOnClickListener(this);
        OK.setOnClickListener(this);
        //showTime(hour, min);
    }



    public String showTime(int hour, int min, TextView time) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour = hour;
            format = "PM";
        } else {
            format = "AM";
        }


        String s = new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format).toString();
        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));


        return s;


    }

    String MyTime2, MyTime3;

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:

                int hour = (timePicker2.getCurrentHour());
                int min = (timePicker2.getCurrentMinute());
                MyTime2 = showTime(hour, min, time2);

                Log.e("time=========", MyTime2);


                break;
            case R.id.button2:
                hour = timePicker2.getCurrentHour();
                min = timePicker2.getCurrentMinute();
                MyTime3 = showTime(hour, min, time3);

                Log.e("end_time=========", MyTime3);

                break;

            case R.id.button3:
                Log.e("=======", "Ok");
                Toast.makeText(Time_for_night_brightness.this, "The phone will be vibrated in the specified interval", Toast.LENGTH_LONG).show();
                Intent in = new Intent();
                in.setAction("MyBroadcast");
                in.putExtra("time2", MyTime2);
                in.putExtra("time3", MyTime3);
                sendBroadcast(in);
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //  this.registerReceiver(recei)
    }
}
