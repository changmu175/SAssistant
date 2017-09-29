package com.dm.ycm.yassitant.utils;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.dm.ycm.yassitant.R;


/**
 * Created by ycm on 2017/5/25.
 * Description:
 * Modified by:
 */

public class LSUtil {

    /**
     * 锁屏
     *
     * @param context       上下文
     * @param componentName 组件名称
     */
    public static void lockScreen(Context context, ComponentName componentName) {
        if (context == null) {
            return;
        }

        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.lockNow();
        }
    }

    /**
     * 唤醒屏幕
     *
     * @param context 上下文
     */
    public static void awakeScreen(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "TAG");
        wakeLock.acquire();
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock("unlock");
        lock.disableKeyguard();
        wakeLock.release();
    }

    /**
     * 检查管理员权限
     *
     * @return 是否有权限
     */
    public static boolean checkPermission(Context context, ComponentName componentName) {
        if (context == null || componentName == null) {
            return false;
        }

        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        return devicePolicyManager.isAdminActive(componentName);
    }

    /**
     * 进入设备管理设置
     */
    public static void activeManager(Context context, ComponentName componentName) {
        if (context == null || componentName == null) {
            return;
        }
        //使用隐式意图调用系统方法来激活指定的设备管理器
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, context.getString(R.string.one_key_lock_screen));
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



//    public static void turnLightOn(Camera mCamera) {
//        if (mCamera == null) {
//            return;
//        }
//        Camera.Parameters parameters = mCamera.getParameters();
//        if (parameters == null) {
//            return;
//        }
//        List<String> flashModes = parameters.getSupportedFlashModes();
//        // Check if camera flash exists
//        if (flashModes == null) {
//            // Use the screen as a flashlight (next best thing)
//            return;
//        }
//        String flashMode = parameters.getFlashMode();
//        if (!Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode)) {
//            // Turn on the flash
//            if (flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
//                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//                mCamera.setParameters(parameters);
//            } else {
//            }
//        }
//    }
//
//    public static void turnLightOff(Camera mCamera) {
//        if (mCamera == null) {
//            return;
//        }
//        Camera.Parameters parameters = mCamera.getParameters();
//        if (parameters == null) {
//            return;
//        }
//        List<String> flashModes = parameters.getSupportedFlashModes();
//        String flashMode = parameters.getFlashMode();
//        // Check if camera flash exists
//        if (flashModes == null) {
//            return;
//        }
//        if (!Camera.Parameters.FLASH_MODE_OFF.equals(flashMode)) {
//            // Turn off the flash
//            if (flashModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
//                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
//                mCamera.setParameters(parameters);
//            } else {
//                Log.e("LSUTIL", "FLASH_MODE_OFF not supported");
//            }
//        }
//    }
//
//    public static void releaseLight(Camera camera) {
//        if (camera != null) {
//            camera.release();
//        }
//    }
}
