/*
package in.btes.battery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

*/
/**
 * Created by Rajat Sharma on 02-04-2018.
 *//*


public class Time extends Activity implements View.OnClickListener {
    private TimePicker timePicker1;
    private TextView time,time1;
    private Calendar calendar;
    private String format = "";
    Button from;
    Button to;
    Button OK;
    int hour,min;
    Date date;
    private AudioManager myAudioManager;
    String checkSlaMissedOrNot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        time = (TextView) findViewById(R.id.textView1);
        time1=findViewById(R.id.textView3);
        from=findViewById(R.id.button);
        to=findViewById(R.id.button2);
        OK=findViewById(R.id.button3);
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
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

    */
/* public void setTime(View view) {
         int hour = timePicker1.getCurrentHour();
         int min = timePicker1.getCurrentMinute();
         showTime(hour, min);
     }
 *//*

    public String showTime(int hour,int min,TextView time) {
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



        return  s;


    }
    String MyTime,MyTime1;
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:

                int hour = (timePicker1.getCurrentHour());
                int min = (timePicker1.getCurrentMinute());
                MyTime=showTime( hour, min,time);

             */
/*  Intent in = new Intent();
               in.setAction("MyBroadcast");
               in.putExtra("time",MyTime);
               sendBroadcast(in);*//*

                Log.e("time=========",MyTime);



                break;
            case R.id.button2:
                hour = timePicker1.getCurrentHour();
                min = timePicker1.getCurrentMinute();
                MyTime1= showTime( hour, min,time1);
               */
/* Intent in1=new Intent();
                in1.putExtra("end_time",MyTime1);
                sendBroadcast(in1);*//*

                Log.e("end_time=========",MyTime1);

                break;

            case R.id.button3:
                Log.e("=======","Ok");
                Toast.makeText(Time.this,"The phone will be vibrated in the specified interval",Toast.LENGTH_LONG).show();
                Intent in = new Intent();
                in.setAction("MyBroadcast");
                in.putExtra("time",MyTime);
                in.putExtra("time1",MyTime1);
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
    */
/* public void broadcastIntent(View view){
        Intent intent = new Intent();
        intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
        sendBroadcast(intent);
    }*//*


}*/
