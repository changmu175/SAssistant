package com.dm.ycm.yassitant.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.dm.ycm.yassitant.GuardActivity;
import com.dm.ycm.yassitant.IMyService;
import com.dm.ycm.yassitant.Listener.ProximitySensorEventListener;
import com.dm.ycm.yassitant.R;
import com.dm.ycm.yassitant.service.aidl.GuardService;
import com.dm.ycm.yassitant.utils.SharePreferenceHelper;

import java.lang.ref.WeakReference;

/**
 * Created by ycm on 2017/5/11.
 * Description:主要服务
 */

public class ProximityListenerService extends Service implements View.OnTouchListener {
    private static final String TAG = ProximityListenerService.class.getSimpleName();
    private SharePreferenceHelper preferenceHelper;
    private ProximitySensorEventListener psListener;
    private ComponentName componentName;
    private IMyService iMyService;
    private ProximityServiceHandler handler;

    //    private MyBinder mBinder;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        //保存服务相关的配置
        handler = new ProximityServiceHandler(this);
        bindService(new Intent(ProximityListenerService.this, GuardService.class), serviceConnection, BIND_AUTO_CREATE);
        preferenceHelper = new SharePreferenceHelper(this, "service");
        preferenceHelper.saveIntegerData("isWorking", 1);
        psListener = new ProximitySensorEventListener(this);
        psListener.registerListener();
        setFLAG_NO_CLEAR_Notification();
    }

    boolean isNeedRestart = true;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
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
        SharePreferenceHelper servicePreferenceHelper = new SharePreferenceHelper(this, "service");
        int isWorking = servicePreferenceHelper.getIntegerData("isWorking");
        isNeedRestart = isWorking == 1;
        try {
            if (iMyService != null) {
                iMyService.isNeedRestart(isNeedRestart);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        cancelNotification();
        unbindService(serviceConnection);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "onTouch");
        return false;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("dddd", "onServiceConnected");
            iMyService = IMyService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("dddd", "onServiceDisconnected");
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    private static class ProximityServiceHandler extends Handler {
        WeakReference<ProximityListenerService> mWeakReference;

        ProximityServiceHandler(ProximityListenerService weak) {
            if (weak != null) {
                mWeakReference = new WeakReference<>(weak);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent();
            intent.setClass(mWeakReference.get(), GuardService.class);
            mWeakReference.get().startService(intent);
        }
    }

    public void setFLAG_NO_CLEAR_Notification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        long when = System.currentTimeMillis();
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        Intent intent = new Intent(this, GuardActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                .setLargeIcon(icon)
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle("防盗服务正在运行").setContentText("点击关闭防盗服务").setTicker("").setWhen(when)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setOngoing(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .build();
        notification.flags = Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;
        notificationManager.notify(0x100, notification);

    }

    private void cancelNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0x100);
    }
}
