package com.dm.ycm.yassitant.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import com.dm.ycm.yassitant.R;
import com.dm.ycm.yassitant.utils.SharePreferenceHelper;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ycm on 2017/5/16.
 * Description:弹窗服务，用于播放音乐和震动
 */

public class DialogService extends Service {
    private MediaPlayer mPlayer;
    private Vibrator vibrator;
    private AlarmThread thread;
    private Camera camera;
    private Parameters parameters;
    @Override
    public void onCreate() {
        super.onCreate();
        SharePreferenceHelper phoneGuardSPHelper = new SharePreferenceHelper(this, "phone_guard");
        int vibrate = phoneGuardSPHelper.getIntegerData("vibrate");
        int light = phoneGuardSPHelper.getIntegerData("light");
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        //设置声音
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, max, 0);
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        //设置可以重复播放
        mPlayer.setLooping(true);
        mPlayer.start();
        if (vibrate == 1) {
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(new long[]{1000, 1000}, 0);
        }

        if (light == 1) {
            thread = new AlarmThread();
            thread.start();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopBellAndVibrator();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 停止响铃和震动
     */
    private void stopBellAndVibrator() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
        }

        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.cancel();
        }

        if (thread != null) {
            thread.cancel();
        }
    }


    private class AlarmThread extends Thread {
        Timer timer = new Timer();
        TimerTask timerTask;
        void cancel() {
            if (timerTask != null) {
                timerTask.cancel();
            }

            if (timer != null) {
                timer.cancel();
            }
            if (camera != null) {
                camera.release();
                camera = null;
            }

        }

        public void run() {
            try {
//                Timer timer = new Timer();
                timerTask = new TimerTask() {
                    public void run() {
//                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            camera = Camera.open();
                            parameters = camera.getParameters();
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            camera.setParameters(parameters);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        try {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            camera.setParameters(parameters);
                            camera.release();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
//                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            // your code using Camera2 API here - is api 21 or higher
//                        }

                    }
                };
                timer.schedule(timerTask, new Date(), 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
