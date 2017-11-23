package com.ar.master.ar;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by Master on 20.11.2017.
 */

public class RequestPermissionsActivity extends AppCompatActivity{
    int REQUEST_PERMISSION = 0;
    int PERMISSION_ALL = 100;
    private static final int
            requestCode_CAMERA = 1,
            requestCode_INTERNET = 2,
            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 3,
            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 4,
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 5
                    ;



    String[] permissionsNormal = new String[]{
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BROADCAST_STICKY,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.DISABLE_KEYGUARD,
            Manifest.permission.EXPAND_STATUS_BAR,
            Manifest.permission.GET_PACKAGE_SIZE,
            Manifest.permission.INTERNET,
            Manifest.permission.KILL_BACKGROUND_PROCESSES,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.NFC,
            Manifest.permission.READ_SYNC_SETTINGS,
            Manifest.permission.READ_SYNC_STATS,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.REORDER_TASKS,
            Manifest.permission.SET_ALARM,
            Manifest.permission.SET_TIME_ZONE,
            Manifest.permission.SET_WALLPAPER,
            Manifest.permission.SET_WALLPAPER_HINTS,
            Manifest.permission.VIBRATE,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.WRITE_SYNC_SETTINGS,
            /*permissionsDangerous*/
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            /*permissionsMin16*/
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            /*permissionsMin19*/
            Manifest.permission.TRANSMIT_IR,
            Manifest.permission.UNINSTALL_SHORTCUT,
            Manifest.permission.INSTALL_SHORTCUT,
            /*permissionsMin20*/
            Manifest.permission.BODY_SENSORS,
            /*permissionsMin23*/
            Manifest.permission.USE_FINGERPRINT,
            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.ACCESS_NOTIFICATION_POLICY
    };
    /*@Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Toast.makeText(RequestPermissionsActivity.this, "code:"+requestCode+" grantResults"+grantResults, Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {// permission was granted, yay! Do the contacts-related task you need to do.

                }
                else {// permission denied, boo! Disable the functionality that depends on this permission.

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }*/


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            // for each permission check if the user granted/denied them
            // you may want to group the rationale in a single dialog,
            // this is just an example
            int len = permissions.length;
            for (int i = 0; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    // user rejected the permission
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(RequestPermissionsActivity.this, permission );
                    if (! showRationale) {
                        // user also CHECKED "never ask again"
                        // you can either enable some fall back,
                        // disable features of your app
                        // or open another dialog explaining
                        // again the permission and directing to
                        // the app setting

                    } else if (Manifest.permission.WRITE_CONTACTS.equals(permission)) {
                        // todo define strings
                        showRationale(permission, R.string.permission_denied_contacts);
                        // user did NOT check "never ask again"
                        // this is a good place to explain the user
                        // why you need the permission and ask if he wants
                        // to accept it (the rationale)
                    }
                    //else if ( /* possibly check more permissions...*/ ) {}
                }
            }
        }
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void goToAppSettings(){
    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.requestpermissions_main);
        Button ok = findViewById(R.id.btn_Ok);
        Button cancel = findViewById(R.id.btn_Cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!hasPermissions(RequestPermissionsActivity.this, permissionsNormal)){
                    ActivityCompat.requestPermissions(RequestPermissionsActivity.this, permissionsNormal, PERMISSION_ALL);
                }


                    /*if (ActivityCompat.shouldShowRequestPermissionRationale( RequestPermissionsActivity.this, Manifest.permission.CAMERA)) {
                        ActivityCompat.requestPermissions(RequestPermissionsActivity.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                    else{
                        ActivityCompat.requestPermissions(RequestPermissionsActivity.this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                    }*/
            }




        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
