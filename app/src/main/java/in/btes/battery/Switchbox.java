package in.btes.battery;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Rajat Sharma on 02-04-2018.
 */

public class Switchbox extends AppCompatActivity{
    private static final String TAG = "MainActivity";


    Switch battery_saver,working_hrs,Do_not_disturb_night,reduce_brightness_night,increase_brightness_sun;
    TextView textView;
    Context context;
    int MY_PERMISSIONS_REQUEST_WRITE_SETTINGS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switchbox);

        permissionCheck();

        battery_saver = findViewById(R.id.switch1);
        working_hrs=findViewById(R.id.switch2);
        Do_not_disturb_night=findViewById(R.id.switch3);
        reduce_brightness_night=findViewById(R.id.switch4);
        increase_brightness_sun=findViewById(R.id.switch5);

        battery_saver.setChecked(false);
        working_hrs.setChecked(false);
        battery_saver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (battery_saver.isChecked()){
                    Intent in=new Intent(Switchbox.this,BatteryActivity.class);
                    startActivity(in);

                }
        }});

        working_hrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(working_hrs.isChecked()){
                    Intent in=new Intent(Switchbox.this,Time.class);
                    startActivity(in);
                }
            }
        });


      Do_not_disturb_night.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(Do_not_disturb_night.isChecked())
              {
                  Intent in=new Intent(Switchbox.this,Time.class);
                  startActivity(in);
              }
          }
      });

        reduce_brightness_night.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(reduce_brightness_night.isChecked()){


                   boolean b=Settings.System.canWrite(Switchbox.this);
                   if(b==true)
                   {
                       Log.e("==========","You are eligible ");
                       Settings.System.canWrite(Switchbox.this);
                       Intent in=new Intent(Switchbox.this,Reduce_brightness_night.class);
                       startActivity(in);


                   }
                   else
                   {
                       Settings.System.canWrite(Switchbox.this);
                       Intent in=new Intent(Switchbox.this,Reduce_brightness_night.class);
                       startActivity(in);
                   }

                }
            }
        });


        increase_brightness_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(increase_brightness_sun.isChecked())
                {
                    boolean b= false;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        b = Settings.System.canWrite(Switchbox.this);
                    }
                    if(b==true)
                    {
                        Log.e("==========","You are eligible ");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Settings.System.canWrite(Switchbox.this);
                        }
                        Intent in=new Intent(Switchbox.this,Reduce_brightness_night.class);
                        startActivity(in);


                    }
                    else
                    {
                        Log.e(";;;;;;;;;;;;","not eligible");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Settings.System.canWrite(Switchbox.this);
                        }
                        Intent in=new Intent(Switchbox.this,Reduce_brightness_night.class);
                        startActivity(in);
                    }
                }
            }
        });





    }

    public void permissionCheck()
    {
Log.e("////////////","Checking Permission");
// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(Switchbox.this,
                Manifest.permission.WRITE_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e("////////////","Checking Permission  1");
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Switchbox.this,Manifest.permission.WRITE_SETTINGS)) {
                Log.e("////////////","Checking Permission 2");
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(Switchbox.this,new String[]{Manifest.permission.WRITE_SETTINGS},MY_PERMISSIONS_REQUEST_WRITE_SETTINGS);
                Log.e("////////////","Checking Permission 3");

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Log.e("////////////","Checking Permission 4");
            // Permission has already been granted
        }
    }






    }
