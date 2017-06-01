package com.dm.ycm.yassitant;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.dm.ycm.yassitant.dialog.AlarmActivity;
import com.dm.ycm.yassitant.receiver.LockReceiver;
import com.dm.ycm.yassitant.service.ProximityListenerService;
import com.dm.ycm.yassitant.utils.ActivityStack;
import com.dm.ycm.yassitant.utils.SharePreferenceHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ComponentName componentName;
//    private TextView textView;
//    private ToggleButton toggleButton;
//    private EditText time;
//    private Button ok;
    private LinearLayout guard_ll;
    private LinearLayout about_app_ll;
    private SharePreferenceHelper servicePreferenceHelper;
    private SharePreferenceHelper timePreferenceHelper;
    private Camera camera;
    private Camera.Parameters parameters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().pushActivity(this);
        setContentView(R.layout.activity_main);
        servicePreferenceHelper = new SharePreferenceHelper(this, "service");
        timePreferenceHelper = new SharePreferenceHelper(this, "time");
        guard_ll = (LinearLayout) findViewById(R.id.guard);
        about_app_ll = (LinearLayout) findViewById(R.id.about_app_ll);
        guard_ll.setOnClickListener(this);
        about_app_ll.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int isWorking = servicePreferenceHelper.getIntegerData("isWorking");
        //开关打开，有管理员权限，但是服务没启动，则启动服务
        if (isWorking == 1) {
            startService();
        } else {
            stopService();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().popActivity(this, false);
    }

    private void startService() {
        Intent intent = new Intent();
        intent.setClass(getBaseContext(), ProximityListenerService.class);
        startService(intent);
    }

    private void stopService() {
        boolean isRemindActivityRunning = ActivityStack.getInstance().getActivityState(AlarmActivity.class);
        if (isRemindActivityRunning) {
            ActivityStack.getInstance().popActivityByClass(AlarmActivity.class, true);
        }
        Intent intent = new Intent();
        intent.setClass(getBaseContext(), ProximityListenerService.class);
        stopService(intent);
    }

    /**
     * 检查管理员权限
     *
     * @return 是否有权限
     */
    private boolean checkPermission() {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, LockReceiver.class);
        return devicePolicyManager.isAdminActive(componentName);
    }

    /**
     * 进入设备管理设置
     */
    private void activeManager() {
        //使用隐式意图调用系统方法来激活指定的设备管理器
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "一键锁屏");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == guard_ll) {
            Intent intent = new Intent();
            intent.setClass(this, GuardActivity.class);
            startActivity(intent);
        } else if (v == about_app_ll) {
            Intent intent = new Intent();
            intent.setClass(this, AboutActivity.class);
            startActivity(intent);
        }
    }
}
