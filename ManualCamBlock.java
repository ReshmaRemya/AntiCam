package com.example.anticam.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.anticam.CamDesable.AntiCamActivator;
import com.example.anticam.CamDesable.DeviceAdminDialog;
import com.example.anticam.R;

public class ManualCamBlock extends AppCompatActivity {

    Context mContext;


    Button btnblock,btnenable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_cam_block);


        mContext=this;



        btnblock=findViewById(R.id.manual_camblock_btnblock);
        btnenable=findViewById(R.id.manual_camblock_btnenable);
        btnblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AntiCamActivator.toggleLensCap(mContext); //false
                updateUI();
            }
        });

        btnenable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AntiCamActivator.toggleLensCap(mContext); //true
                updateUI();
            }
        });


    }


    private void updateUI() {


        AntiCamActivator.Status cameraStatus = AntiCamActivator.getStatus(mContext);

//        switch (cameraStatus) {
//            case CAMERA_DISABLED:
//                setAdminButton.setVisibility(GONE);
//
//                lensCapStatus.setText(getText(R.string.lens_cap_status_on));
//                lensCapStatus2.setText(getText(R.string.lens_cap_status_on2));
//
//                imageButton.setImageResource(R.drawable.lenscap);
//                break;
//
//            case CAMERA_ENABLED:
//                setAdminButton.setVisibility(GONE);
//                imageButton.setEnabled(true);
//
//                lensCapOnButton.setEnabled(true);
//                lensCapOffButton.setEnabled(false);
//
//                lensCapStatus.setText(getText(R.string.lens_cap_status_off));
//                lensCapStatus2.setText(getText(R.string.lens_cap_status_off2));
//
//                imageButton.setImageResource(R.drawable.lens);
//                break;
//            default:
//                setAdminButton.setVisibility(View.VISIBLE);
//
//                lensCapOnButton.setEnabled(false);
//                lensCapOffButton.setEnabled(false);
//
//                imageButton.setEnabled(false);
//                imageButton.setImageResource(R.drawable.lens);
//                break;
//        }

//        updateWidget();
    }



    @Override
    protected void onResume() {

        super.onResume();
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


                break;
        }

    }

    void callPermission()
    {
        DialogFragment newFragment = new DeviceAdminDialog();
        newFragment.show(getFragmentManager(), "deviceAdmin");
    }



}
