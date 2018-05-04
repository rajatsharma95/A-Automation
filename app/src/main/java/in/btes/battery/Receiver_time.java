package in.btes.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 * Created by Rajat Sharma on 05-04-2018.
 */



public class Receiver_time extends BroadcastReceiver {
    AudioManager myAudioManager;
    String settime,settime1;
    final long period=0;
    boolean chk;


    @Override
    public void onReceive(Context context, Intent intent) {
        myAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        Date currenttime = Calendar.getInstance().getTime();
        settime = intent.getStringExtra("time");
        settime1 = intent.getStringExtra("time1");
        Log.e("=========","In Receiver");



        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    chk=checkSlaMissedOrNot(settime,settime1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        },0,1000);



                if (chk == true) {
                    Log.e("=========", "Phone vibrates");
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                } else /*if (chk == false)*/{

                    Log.e("=========", "Phone not vibrating");
                   // checkSlaMissedOrNot(settime);
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);


                }


    }

    public boolean checkSlaMissedOrNot(String sla,String sla1) throws ParseException {
        boolean slaMissedOrnot = false;
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();

        Log.e("time/////",sla);

        /*SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        */


        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
        int min1 = cal1.get(Calendar.MINUTE);

        Log.e("********************","Hour is"+hour);

        if ((hour>0 && hour<10 && min>=0 && min <10)) {                    //done

            Log.e("=============","9:01");

            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla.substring(0, 1)));
            cal.set(Calendar.MINUTE, Integer.parseInt(sla.substring(4,5)));
            cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla1.substring(0, 1)));
            cal1.set(Calendar.MINUTE, Integer.parseInt(sla1.substring(4,5)));
        }
         else if( (hour>0 && hour<10) && (min>9 && min <=59) )
        {
            Log.e("=============","3:45");                          //done

            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla.substring(0,1)));
            cal.set(Calendar.MINUTE, Integer.parseInt(sla.substring(4,6)));
            cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla1.substring(0,1)));
            cal1.set(Calendar.MINUTE, Integer.parseInt(sla1.substring(4,6)));
        }
        /*else if( (hour>9 && hour<=12) && (min>9 && min<=59 ))
        {
            Log.e("=============","12:45");                         //done
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla.substring(0,2)));
           cal.set(Calendar.MINUTE, Integer.parseInt(sla.substring(5,7)));
        }*/

       else if( (hour>9 && hour<=12) && (min >0 && min<10))
        {
            Log.e("=============","11 : 9");                         //done
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(sla.substring(5,6)));
            cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla1.substring(0, 2)));
            cal1.set(Calendar.MINUTE, Integer.parseInt(sla1.substring(5,6)));


        }

        else if((hour>=12 && hour<=24) && (min>0 && min<10)) //EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        {
            Log.e("=============","12:02 PM");
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla.substring(0,1)));
            cal.set(Calendar.MINUTE, Integer.parseInt(sla.substring(5,7)));
            cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla.substring(0,1)));
            cal1.set(Calendar.MINUTE, Integer.parseInt(sla1.substring(5,7)));

        }

        else if((hour>=12 && hour<=24) && (min>9 && min<=59)) //EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        {

            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla.substring(0,2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(sla.substring(5,7)));
            cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla1.substring(0,2)));
            cal1.set(Calendar.MINUTE, Integer.parseInt(sla1.substring(5,7)));
            Log.e("====11=======","---"+hour+" : "+min);
        }




        else
        {
            Log.e("=============","---"+hour+" : "+min);
            Log.e("=============","ELSE");
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla.substring(0,2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(sla.substring(5,7))); //EEEEEEEEEEEEEEeeif time is 12:39, time is 12:02 PM
            cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sla1.substring(0,2)));
            cal1.set(Calendar.MINUTE, Integer.parseInt(sla1.substring(5,7))); //EEEEEEEEEEEEEEeeif time is 12:39, time is 12:02 PM


        }
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Log.e("=========", "In Method");

        Calendar current_cal1 = Calendar.getInstance();
       // cal1.set(Calendar.HOUR,0);

            if (current_cal1.after(cal) && current_cal1.before(cal1)  ) { //if current time is after the entered time
              //  Log.e("==============", "in the if condition"+cal);
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Log.e("=========", "Phone vibrates");
                slaMissedOrnot = true;

            }

            else {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);


                //Log.e("=====0000=====", "in the else condition"+cal1);
               // Log.e("=====1111=====", "in the else condition"+cal);

                slaMissedOrnot = false;
                Log.e("=========", "Phone not vibrates");

            }

            return slaMissedOrnot;
        }


}


