package com.example.anticam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticam.CamDesable.AntiCamActivator;
import com.example.anticam.CamDesable.DeviceAdminDialog;
import com.example.anticam.Common.GPSTracker;
import com.example.anticam.Common.Utility;
import com.example.anticam.User.UserHome;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PermissionController extends AppCompatActivity {


    GPSTracker gps;
    double latitude;
    double longitude;
    Context mContext;
    double val=0.0;
    String lattlong[];

    private Timer mTimer1;
    private TimerTask mTt1;
    private Handler mTimerHandler = new Handler();

    ImageButton btngrant;

    Button btnblock,btnenable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_permission_controller);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();


        mContext = this;
        gps = new GPSTracker(getApplicationContext());

        btngrant=findViewById(R.id.permission_controller_btngrant);


        btngrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermisssion();
            }



        });


        getDisableLatLong();

    }


    private void updateUI() {


        AntiCamActivator.Status cameraStatus = AntiCamActivator.getStatus(mContext);

    }



    @Override
    protected void onResume() {

        super.onResume();
        getDisableLatLong();
        checkPermisssion();
    }

    void checkPermisssion(){

        AntiCamActivator.Status cameraStatus = AntiCamActivator.getStatus(mContext);

        switch (cameraStatus) {
            case DEVICE_ADMIN_DISABLED:

                Toast.makeText(getApplicationContext(), "DEVICE_ADMIN_DISABLED", Toast.LENGTH_SHORT).show();
                callPermission();
//                mDPM.setCameraDisabled(mDeviceAdminSample, false);
//                status = context.getResources().getString(R.string.lens_cap_status_off);
                break;
            default:

                Toast.makeText(getApplicationContext(), "DEVICE_ADMIN_ENABLED", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(getApplicationContext(), UserHome.class));
                startActivity(new Intent(getApplicationContext(), UserHome.class));
                break;
        }

    }

    void callPermission()
    {
        DialogFragment newFragment = new DeviceAdminDialog();
        newFragment.show(getFragmentManager(), "deviceAdmin");
    }

    private void startTimer() {
        mTimer1 = new Timer();
        mTt1 = new TimerTask() {
            public void run() {
                mTimerHandler.post(new Runnable() {
                    public void run() {

                        try {

                           // getDisableLatLong();
                            workdone();

//                            startService(new Intent(getApplicationContext(), BackgroundService.class));


                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(), "Service exception\n " + e, Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }
        };
        mTimer1.schedule(mTt1, 1, 10000);
    }


    void workdone(){
        //...................9.981300, 76.275434


        double locdist=300;

        Utility.LOCK_DISTANCE=locdist;
        String arlatt[]=lattlong[0].split("#");
        String arlongg[]=lattlong[1].split("#");

        boolean distlength=false;


        Toast.makeText(getApplicationContext(), "arlatt.length  :" +arlatt.length, Toast.LENGTH_SHORT).show();

        for (int i= 0;i<arlatt.length;i++){

            double distval = Utility.distanceFinder(gps.getLatitude(), gps.getLongitude(),Double.parseDouble(arlatt[i]), Double.parseDouble(arlongg[i]), 'K');
            val=distval*1000;
            Toast.makeText(getApplicationContext(), "dist  : "+val, Toast.LENGTH_SHORT).show();
            if((distval*1000) < locdist ){
                distlength=true;
            }
        }


        if(distlength){
            AntiCamActivator.toggleAntiCam(mContext,"desable"); //false
//            AntiCamActivator.Status cameraStatus = AntiCamActivator.getStatus(mContext);
            Toast.makeText(getApplicationContext(), "inside Rescrict Area "+val, Toast.LENGTH_LONG).show();
        }else{
            AntiCamActivator.toggleAntiCam(mContext,"enable"); //false
//            AntiCamActivator.Status cameraStatus = AntiCamActivator.getStatus(mContext);
            Toast.makeText(getApplicationContext(), "Outside Rescrict Area "+val, Toast.LENGTH_LONG).show();
        }



//        ..................
    }


    public void getDisableLatLong() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    lattlong=response.split("&");

                    startTimer();
                } else {
                    Toast.makeText(getApplicationContext(), "noval..!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "getDisableLatLong");

                return map;
            }
        };
        queue.add(request);
    }

}
