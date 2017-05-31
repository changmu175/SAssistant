package com.dm.ycm.yassitant.Listener;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.dm.ycm.yassitant.dialog.AlarmActivity;
import com.dm.ycm.yassitant.receiver.LockReceiver;
import com.dm.ycm.yassitant.utils.ActivityStack;
import com.dm.ycm.yassitant.utils.LSUtil;
import com.dm.ycm.yassitant.utils.LockScreenUtil;
import com.dm.ycm.yassitant.utils.SharePreferenceHelper;


/**
 * Created by ycm on 2017/5/17.
 *Description:距离传感器
 */

public class ProximitySensorEventListener implements SensorEventListener /*LTCallback.PhoneStateCallback*/{
    private float firstValue = 100;
    private float secondValue = 0;
    private Context context;
    private int tempValue = 0;
    private SensorManager sensorManager;
    private Sensor sensor;
    private static boolean isCalling = false;
//    private ComponentName componentName;
    private SharePreferenceHelper phoneGuardSPHelper;
    /**
     * 设置是否正在通话
     * @param isCalling 是否正在通话
     */
    static void setIsCalling(boolean isCalling) {
        ProximitySensorEventListener.isCalling = isCalling;
    }

    /**
     * 构造函数
     * @param context 上下文
     */
    public ProximitySensorEventListener(Context context) {
        this.context = context;
//        this.componentName = new ComponentName(context, LockReceiver.class);
        this.phoneGuardSPHelper = new SharePreferenceHelper(context, "phone_guard");
    }

    /**
     * 注册传感器
     */
    public void registerListener() {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new MyPhoneState(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    /**
     * 取消注册
     */
    public void unregisterListener() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this, sensor);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("dealSensorEvent", isCalling + "");
        Log.d("kkkkkkkkkkkk", sensor.getMaximumRange() + "");
        //正在通话则返回
        if (isCalling) {
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float value = event.values[0];
            //处理传感器的值
            dealSensorEvent(value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    /**
     * 处理光敏事件
     *
     * @param value 光值
     */
    private void dealSensorEvent(float value) {
        Log.d("dealSensorEvent", "dealSensorEvent");
        if (value == sensor.getMaximumRange()) {
            if (tempValue == 1) {
                tempValue = 2;
            }
        } else {
            tempValue = 1;
        }

        if (value == 0.0) {
            firstValue = value;
        } else {
            secondValue = value;
        }
        Log.d("LTService", secondValue + "");
        if (tempValue == 2) {
            Log.d("LTService---firstValue", firstValue + "");
            Log.d("LTService---secondValue", secondValue + "");
            firstValue = 100;
            secondValue = 0;
            tempValue = 0;
            //判断是否亮屏
            if (LockScreenUtil.isScreenOn(context)) {
                ComponentName componentName = new ComponentName(context, LockReceiver.class);
                LSUtil.lockScreen(context, componentName);
            } else {
                LSUtil.awakeScreen(context);
                showDialog();
            }
        }
    }

    /**
     * 显示弹窗
     */
    private void showDialog() {
        boolean isRunning = ActivityStack.getInstance().getActivityState(AlarmActivity.class);
        if (isRunning) {
            Log.d("hhhhh", "没有重启Activity");
            LSUtil.awakeScreen(context);
            return;
        }
        Log.d("hhhhh", "重启Activity");
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, AlarmActivity.class);
        context.startActivity(intent);
    }

//    @Override
//    public void startListening() {
//        registerListener();
//    }
//
//    @Override
//    public void stopListening() {
//        unregisterListener();
//    }
}
