package com.dm.ycm.yassitant.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.dm.ycm.yassitant.Listener.ProximitySensorEventListener;
import com.dm.ycm.yassitant.receiver.LockReceiver;
import com.dm.ycm.yassitant.utils.SharePreferenceHelper;

/**
 * Created by ycm on 2017/5/11.
 *Description:主要服务
 */

public class LTService extends Service implements View.OnTouchListener {
    private static final String TAG = LTService.class.getSimpleName();
    private SharePreferenceHelper preferenceHelper;
    private ProximitySensorEventListener psListener;
    private ComponentName componentName;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        //保存服务相关的配置

        preferenceHelper = new SharePreferenceHelper(this, "service");
        preferenceHelper.saveIntegerData("isWorking", 1);
        psListener = new ProximitySensorEventListener(this);
        psListener.registerListener();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        //退出时释放传感器监听
        if (psListener != null) {
            psListener.unregisterListener();
        }

        //保存正在工作为步不再工作
        if (preferenceHelper != null) {
            preferenceHelper.saveIntegerData("isWorking", 0);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "onTouch");
        return false;
    }
}
