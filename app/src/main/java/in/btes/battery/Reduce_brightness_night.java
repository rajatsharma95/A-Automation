package in.btes.battery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rajat Sharma on 23-04-2018.
 */


public class Reduce_brightness_night extends Activity {


    //Variable to store brightness value
     private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            //Get the content resolver
            cResolver = getContentResolver();

//Get the current window
            window = getWindow();


            try
            {
                // To handle the auto
                Log.e("Error", "Accessing the battery info");
                Settings.System.putInt(cResolver,Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
                //Get the current system brightness
                brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);

            }
            catch (Settings.SettingNotFoundException e)
            {
                //Throw an error case it couldn't be retrieved
                Log.e("Error", "Cannot access system brightness");
                e.printStackTrace();

            }

            //Set the system brightness using the brightness variable value


            Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
            //Get the current window attributes
          WindowManager.LayoutParams layoutpars = window.getAttributes();
            //Set the brightness of this window
            layoutpars.screenBrightness = brightness / (float)255;
            //Apply attribute changes to this window
            window.setAttributes(layoutpars);

        }



}










