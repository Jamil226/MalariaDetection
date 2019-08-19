package com.example.fsm;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        
        if (!isConnected(Splash.this)) buildDialog(Splash.this).show();
        else {
            //Toast.makeText(Splash.this,"Welcome", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_splash);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(5000);
                        Intent intent = new Intent(Splash.this, FSM.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    super.run();
                }
            };
            thread.start();
        }

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Wifi to Access Application. Click on Allow to Enable Wifi.");

        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                ActivityCompat.requestPermissions(Splash.this, new String[]{Manifest.permission.ACCESS_WIFI_STATE}, PERMISSION_WIFI_CODE);
//                Intent turnWifiOn = new Intent(Settings.ACTION_WIFI_SETTINGS);
//                startActivity(turnWifiOn);
                wifiManager.setWifiEnabled(true);
                setContentView(R.layout.activity_splash);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(10000);
                            Intent intent = new Intent(Splash.this, FSM.class);
                            startActivity(intent);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        super.run();
                    }
                };
                thread.start();
            }
        });
        builder.setNegativeButton("Don't Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        return builder;
    }

}
