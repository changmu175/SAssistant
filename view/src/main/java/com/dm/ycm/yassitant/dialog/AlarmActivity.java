package com.dm.ycm.yassitant.dialog;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.ycm.yassitant.R;
import com.dm.ycm.yassitant.common.ConstDef;
import com.dm.ycm.yassitant.service.DialogService;
import com.dm.ycm.yassitant.utils.ActivityStack;
import com.dm.ycm.yassitant.utils.SharePreferenceHelper;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ycm on 2017/5/15.
 * Description:报警
 */
public class AlarmActivity extends Activity implements View.OnClickListener{
    private LinearLayout alarm_ll;
    private TextView countDownTv;
    private Button closeBtn;
    private static Timer timer;
    private static TimerTask timerTask;
    private CountDownHandler countDownHandler;
    private PoliceLightHandler policeLightHandler;
    private static int count;
    private static PoliceLightThread policeLightThread;
    private HomeKeyEventReceiver homeKeyEventReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        ActivityStack.getInstance().pushActivity(this);
        SharePreferenceHelper timePreferenceHelper = new SharePreferenceHelper(this, ConstDef.CUT_DOWN_TIME);
        count = timePreferenceHelper.getIntegerData(ConstDef.CUT_DOWN_TIME);
        homeKeyEventReceiver = new HomeKeyEventReceiver();
        IntentFilter mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(homeKeyEventReceiver, mFilter);
        countDownHandler = new CountDownHandler(this);
        policeLightHandler = new PoliceLightHandler(this);
        isFlash = true;
        policeLightThread = new PoliceLightThread();
        alarm_ll = (LinearLayout) findViewById(R.id.alarm_ll);
        countDownTv = (TextView) findViewById(R.id.count_down_tv);
        closeBtn = (Button) findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(this);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1;
                countDownHandler.sendMessage(msg);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isFlash = false;
        cancelTask();
        Intent intent = new Intent();
        intent.setClass(this, DialogService.class);
        stopService(intent);
        ActivityStack.getInstance().popActivity(this, false);
        unregisterReceiver(homeKeyEventReceiver);
    }

    @Override
    public void onClick(View v) {
        if (v == closeBtn) {
            finish();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - tempTime > 2000 && clickCount < 3) {
                tempTime = System.currentTimeMillis();
                Log.d("++++", tempTime +"");
            } else {
                finish();
            }
        }

            return super.onTouchEvent(event);
    }

    /**
     * 判断是否点击了按钮
     *
     * @param view 按钮
     * @param ev   事件
     * @return 是否按了按钮
     */
    private boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return !(ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight()));
    }

    /**
     * 倒计时handler
     */
    private static class CountDownHandler extends Handler {
        private WeakReference reference;

        CountDownHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            AlarmActivity activity = (AlarmActivity) reference.get();
            switch (msg.what) {
                case 1:
                    count--;
                    if (activity != null) {
                        String str = count + " s";
                        activity.countDownTv.setText(str);

                        if (count <= 0) {
                            cancelTask();

                            startRemindService(activity);

                        }
                    }


            }
            super.handleMessage(msg);
        }
    }

    /**
     * 开启提醒服务
     *
     * @param activity 上下文
     */
    private static void startRemindService(Activity activity) {
        if (activity != null) {
            policeLightThread.start();
            Intent intent = new Intent();
            intent.setClass(activity, DialogService.class);
            activity.startService(intent);
//            SharePreferenceHelper phoneGuardSPHelper = new SharePreferenceHelper(activity, "phone_guard");
//            int light = phoneGuardSPHelper.getIntegerData("light");
//            if (light == 1) {
//                if (!thread.isAlive()) {
//                    thread.start();
//                }
//            }
        }
    }

    /**
     * 取消任务
     */
    private static void cancelTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private long tempTime = 0;
    private int clickCount = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if((event.getAction() == KeyEvent.ACTION_DOWN)) {
//            clickCount ++;
//            if (System.currentTimeMillis() - tempTime > 2000) {
//                Toast.makeText(this, "请在按一次返回退出", Toast.LENGTH_LONG).show();
//                tempTime = System.currentTimeMillis();
//            } else {
//                finish();
//                System.exit(0); //凡是非零都表示异常退出!0表示正常退出!
//            }
//            return true;
//        }

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
//                finish();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.d("KEYCODE_VOLUME", "KEYCODE_VOLUME_DOWN");
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.d("KEYCODE_VOLUME", "KEYCODE_VOLUME_UP");
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 监听Home键
     */
    class HomeKeyEventReceiver extends BroadcastReceiver {
        private final String SYSTEM_REASON = "reason";
        private final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
        private final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
        private final String SYSTEM_DIALOG_REASON_GLOBALACTIONS = "globalactions";
        private int count = 0;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_DIALOG_REASON_HOME_KEY)) {
                    count ++;
                    if (count >= 2) {
                        finish();
                    }
                    Log.d("ACTION_CLOSE_SYSTEM", "homekey");
                } else if (TextUtils.equals(reason, SYSTEM_DIALOG_REASON_GLOBALACTIONS)) {
                    Intent myIntent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                    myIntent.putExtra("myReason", true);
                    context.sendOrderedBroadcast(myIntent, null);
                    Log.d("ACTION_CLOSE_SYSTEM", "globalactions");
                }else {
                    count = 0;
                }
            } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                Log.d("ACTION_SCREEN_OFF", reason);
            }
        }
    }

    boolean isFlash = false;
    private class PoliceLightThread extends Thread {

        @Override
        public void run() {
            super.run();

            while (isFlash) {
                try {
                    policeLightHandler.sendEmptyMessage(Color.BLUE);
                    Thread.sleep(150);
                    policeLightHandler.sendEmptyMessage(Color.BLACK);
                    Thread.sleep(150);
                    policeLightHandler.sendEmptyMessage(Color.RED);
                    Thread.sleep(150);
                    policeLightHandler.sendEmptyMessage(Color.BLACK);
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class PoliceLightHandler extends Handler {
        private WeakReference reference;

        PoliceLightHandler(Activity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AlarmActivity activity = (AlarmActivity) reference.get();
            activity.alarm_ll.setBackgroundColor(msg.what);
        }
    }
}
