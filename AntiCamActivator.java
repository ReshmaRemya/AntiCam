package com.example.anticam.CamDesable;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.anticam.R;

public class AntiCamActivator {

    public static enum Status {
        CAMERA_DISABLED, CAMERA_ENABLED, DEVICE_ADMIN_DISABLED
    }


    public static void toggleAntiCam(Context context,String state){
        DevicePolicyManager mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName mDeviceAdminSample = new ComponentName(context, mDeviceAdminReceiver.class);
        String status;

        Status cameraStatus = getStatus(context);
            if(state.trim().equals("enable")) {
                mDPM.setCameraDisabled(mDeviceAdminSample, false);
                status = context.getResources().getString(R.string.lens_cap_status_off);
            }
            else {
                mDPM.setCameraDisabled(mDeviceAdminSample, true);
                status = context.getResources().getString(R.string.lens_cap_status_on);
            }

        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }

    public static void toggleLensCap(Context context) {
        DevicePolicyManager mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName mDeviceAdminSample = new ComponentName(context, mDeviceAdminReceiver.class);
        String status;

        Status cameraStatus = getStatus(context);
        switch (cameraStatus) {
            case CAMERA_DISABLED:
                mDPM.setCameraDisabled(mDeviceAdminSample, false);
                status = context.getResources().getString(R.string.lens_cap_status_off);
                break;
            case CAMERA_ENABLED:
                mDPM.setCameraDisabled(mDeviceAdminSample, true);
                status = context.getResources().getString(R.string.lens_cap_status_on);
                break;
            default:
                // If no device administrator, send the user straight to the settings page with a help toast
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings","com.android.settings.DeviceAdminSettings"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                status = context.getResources().getString(R.string.error_no_device_admin);
                break;
        }
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }

    public static Status getStatus(Context context) {
        DevicePolicyManager mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName mDeviceAdminSample = new ComponentName(context, mDeviceAdminReceiver.class);
        if (mDPM.isAdminActive(mDeviceAdminSample)) {
            // If the camera is disabled and the method is told to re-enable it
            if (mDPM.getCameraDisabled(mDeviceAdminSample)) {
                return Status.CAMERA_DISABLED;
            } else {
                return Status.CAMERA_ENABLED;
            }
        } else {
            return Status.DEVICE_ADMIN_DISABLED;
        }
    }

//    public static void disableDeviceAdmin(Context context) {
//        if (AntiCamActivator.getStatus(context) != AntiCamActivator.Status.DEVICE_ADMIN_DISABLED) {
//            // If no device administrator, send the user straight to the settings page with a help toast
//            Intent intent = new Intent();
//            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.DeviceAdminSettings"));
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//            Toast.makeText(context, R.string.disable_device_admin_help_1, Toast.LENGTH_LONG).show();
//            Toast.makeText(context, R.string.disable_device_admin_help_2, Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(context, R.string.device_admin_already_disabled, Toast.LENGTH_SHORT).show();
//        }
//    }
}
