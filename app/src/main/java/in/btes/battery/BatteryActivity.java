package in.btes.battery;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.ECLAIR)
public class BatteryActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    ImageView iv_battery;
    TextView tv_battery;
    Handler handler;
    Runnable runnable;
    WifiManager wifiManager;
    BluetoothAdapter mBluetoothAdapter;
    Button snackbar;
    Switch battery_saver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE); //WIFI
        iv_battery = findViewById(R.id.iv_battery);
        tv_battery = findViewById(R.id.tv_battery);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


            runnable = new Runnable() { //When an object implementing interface <code>Runnable</code> is used to create a thread, starting the thread causes the object <code>run</code> method to be
                // called in that separately executing thread.

                @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
                //Applications targeting this or a later release will get these new changes in behavior
                @Override
                public void run() {
                    int level = (int) batteryLevel(); // getting the battery current level
                    tv_battery.setText(" BATTERY: " + level + "%");

                    if (level > 95) {
                        iv_battery.setImageResource(R.drawable.battery_100);
                    }

                    if (level > 65 && level <= 95) {
                        iv_battery.setImageResource(R.drawable.battery_75);
                    }

                    if (level > 31 && level < 65) {
                        iv_battery.setImageResource(R.drawable.battery_50);
                    }
                    if (level > 15 && level <= 30) {
                        iv_battery.setImageResource(R.drawable.battery_25);

                        wifiManager.setWifiEnabled(false);  //disabling the WIFI

                        Log.v(TAG, "Calling function: enabledisableBT()");

                        DisableBT();

                        Toast.makeText(BatteryActivity.this, "WIFI and Bluetooth both are Disabled", Toast.LENGTH_LONG).show();
                        ///////Whatsapp text
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/bold");
                        i.putExtra(Intent.EXTRA_TEXT, "The charging of phone is low");
                        i.setPackage("com.whatsapp");

                        // i.putExtra(Intent.EXTRA_SUBJECT,"Subject");
                        // i.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps");
                        //  i.putExtra(Intent.EXTRA_TEXT,"hi");
                        startActivity(i);
                        ///////// sent text to Whats-app
                    }
                    if (level > 0 && level <= 15) {
                        iv_battery.setImageResource(R.drawable.battery_0);
                    }

                    // handler.postDelayed(runnable, 20000); //after every 5 secs this will run
                }
            };


            handler = new Handler();
            handler.postDelayed(runnable, 2000); // when the app will run then it will check the battery status after 10 secs(10000 milliseconds)
        }


    public float batteryLevel() {
        Intent batteryintent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryintent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryintent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        /*if (level == -1 || scale == -1) {
            return 50.0f;
        }*/
         return (float) level; // (if we write this then in the logcat we get the brokenpipe erroe)
        // return (float) level / (float) scale * 100.0f;
    }

    public void DisableBT(){

        if(mBluetoothAdapter == null){   // if the phone doen't have bluetooth

            Log.d(TAG, "enableDisableBT: Does not have BT capabilities.");

        }

     /*   if(!mBluetoothAdapter.isEnabled()){  //if bluetooth is disabled
            Log.d(TAG, "enableDisableBT: enabling BT.");

            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivity(enableBTIntent);



            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);

            registerReceiver(mBroadcastReceiver1, BTIntent);

        }*/

        if(mBluetoothAdapter.isEnabled()){  // IF THE BLUETOOTH IS ENABLED

            Log.d(TAG, "enableDisableBT: disabling BT.");

            mBluetoothAdapter.disable();   //disable the Bluetooth



          /*  IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);

            registerReceiver(mBroadcastReceiver1, BTIntent);*/

        }
    }
}



