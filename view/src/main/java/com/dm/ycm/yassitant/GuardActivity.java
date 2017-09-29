package com.dm.ycm.yassitant;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.dm.ycm.yassitant.common.ConstDef;
import com.dm.ycm.yassitant.dialog.AlarmActivity;
import com.dm.ycm.yassitant.receiver.LockReceiver;
import com.dm.ycm.yassitant.service.ProximityListenerService;
import com.dm.ycm.yassitant.utils.ActivityStack;
import com.dm.ycm.yassitant.utils.LSUtil;
import com.dm.ycm.yassitant.utils.PermissionUtils;
import com.dm.ycm.yassitant.utils.SharePreferenceHelper;

/**
 * Created by ycm on 2017/5/25.
 * Description:
 * Modified by:
 */

public class GuardActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback{
    private Switch open_guard;
    private LinearLayout lock_ll;
    private Switch lock;
    private LinearLayout vibrate_ll;
    private Switch vibrate;
    private LinearLayout light_ll;
    private Switch light;
    private LinearLayout about_guard_ll;
    private SharePreferenceHelper phoneGuardSPHelper;
    private SharePreferenceHelper servicePreferenceHelper;
    private SharePreferenceHelper timePreferenceHelper;
    private boolean guardIsOpen = false;
    private boolean lockIsOpen = false;
    private boolean vibrateIsOpen = false;
    private boolean lightIsOpen = false;
    private ComponentName componentName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guard_activity);
        timePreferenceHelper = new SharePreferenceHelper(this, ConstDef.CUT_DOWN_TIME);
        timePreferenceHelper.saveIntegerData(ConstDef.CUT_DOWN_TIME, 5);
        servicePreferenceHelper = new SharePreferenceHelper(this, ConstDef.PROXIMITY_SERVICE);
        phoneGuardSPHelper = new SharePreferenceHelper(this, ConstDef.PHONE_GUARD);
        int phoneGuard = phoneGuardSPHelper.getIntegerData(ConstDef.GUARD_IS_OPEN);
        componentName = new ComponentName(this, LockReceiver.class);
        guardIsOpen = phoneGuard == 1;
        if (guardIsOpen) {
            int lock = phoneGuardSPHelper.getIntegerData(ConstDef.LOCK);
            int vibrate = phoneGuardSPHelper.getIntegerData(ConstDef.VIBRATE);
            int light = phoneGuardSPHelper.getIntegerData(ConstDef.LIGHT);
            lockIsOpen = lock == 1;
            vibrateIsOpen = vibrate == 1;
            lightIsOpen = light == 1;
        }
        open_guard = (Switch) findViewById(R.id.open_guard);
        lock_ll = (LinearLayout) findViewById(R.id.lock_ll);
        lock = (Switch) findViewById(R.id.lock);
        vibrate_ll = (LinearLayout) findViewById(R.id.vibrate_ll);
        vibrate = (Switch) findViewById(R.id.vibrate);
        light_ll = (LinearLayout) findViewById(R.id.light_ll);
        about_guard_ll = (LinearLayout) findViewById(R.id.about_guard_ll);
        light = (Switch) findViewById(R.id.light);
        lock.setOnCheckedChangeListener(this);
        vibrate.setOnCheckedChangeListener(this);
        open_guard.setOnCheckedChangeListener(this);
        light.setOnCheckedChangeListener(this);
        about_guard_ll.setOnClickListener(this);
        if (guardIsOpen){
            lock_ll.setVisibility(View.VISIBLE);
            vibrate_ll.setVisibility(View.VISIBLE);
            light_ll.setVisibility(View.VISIBLE);
            about_guard_ll.setVisibility(View.VISIBLE);
        } else {
            lock_ll.setVisibility(View.GONE);
            vibrate_ll.setVisibility(View.GONE);
            light_ll.setVisibility(View.GONE);
            about_guard_ll.setVisibility(View.GONE);
        }
        open_guard.setChecked(guardIsOpen);
        lock.setChecked(lockIsOpen);
        vibrate.setChecked(vibrateIsOpen);
        light.setChecked(lightIsOpen);
    }

    private void setLockState(boolean isVisible, boolean isCheck) {
        if (lock_ll != null) {
            lock_ll.setVisibility(View.VISIBLE);
            lock.setChecked(lockIsOpen);
        }
    }

    private void setVibrateState(boolean isVisible, boolean isCheck) {
        if (vibrate_ll != null) {
            vibrate_ll.setVisibility(View.VISIBLE);
            vibrate.setChecked(vibrateIsOpen);

        }
    }

    private void setSingleViewState(Switch s, boolean isCheck) {
        s.setChecked(isCheck);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int phoneGuard = phoneGuardSPHelper.getIntegerData(ConstDef.GUARD_IS_OPEN);
        guardIsOpen = phoneGuard == 1;
        if (!LSUtil.checkPermission(this, componentName)) {
            phoneGuardSPHelper.saveIntegerData(ConstDef.LOCK, 0);
        }
        if (guardIsOpen) {
            int lock = phoneGuardSPHelper.getIntegerData(ConstDef.LOCK);
            int vibrate = phoneGuardSPHelper.getIntegerData(ConstDef.VIBRATE);
            int light = phoneGuardSPHelper.getIntegerData(ConstDef.LIGHT);
            lockIsOpen = lock == 1;
            vibrateIsOpen = vibrate == 1;
            lightIsOpen = PermissionUtils.checkPermission(this, PermissionUtils.CODE_CAMERA) == PackageManager.PERMISSION_GRANTED && light == 1;
            lock_ll.setVisibility(View.VISIBLE);
            vibrate_ll.setVisibility(View.VISIBLE);
            light_ll.setVisibility(View.VISIBLE);
            about_guard_ll.setVisibility(View.VISIBLE);
        } else {
            lock_ll.setVisibility(View.GONE);
            vibrate_ll.setVisibility(View.GONE);
            light_ll.setVisibility(View.GONE);
            about_guard_ll.setVisibility(View.GONE);
        }
        open_guard.setChecked(guardIsOpen);
        lock.setChecked(lockIsOpen);
        vibrate.setChecked(vibrateIsOpen);
        light.setChecked(lightIsOpen);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == open_guard) {
            if (isChecked) {
                phoneGuardSPHelper.saveIntegerData(ConstDef.GUARD_IS_OPEN, 1);
                startService();
//                initView();
                lock_ll.setVisibility(View.VISIBLE);
                lock.setChecked(false);
                vibrate_ll.setVisibility(View.VISIBLE);
                vibrate.setChecked(false);
                light_ll.setVisibility(View.VISIBLE);
                light.setChecked(false);
                about_guard_ll.setVisibility(View.VISIBLE);
//                setLockState(true, lockIsOpen);
//                setVibrateState(true, vibrateIsOpen);
            } else {
                phoneGuardSPHelper.saveIntegerData("guardIsOpen", 0);
                lock_ll.setVisibility(View.GONE);
                vibrate_ll.setVisibility(View.GONE);
                light_ll.setVisibility(View.GONE);
                about_guard_ll.setVisibility(View.GONE);
                Intent intent = new Intent();
                intent.putExtra("isNeedRestart", false);
                stopService();
//                setLockState(false, lockIsOpen);
//                setVibrateState(false, vibrateIsOpen);
            }
        } else if (buttonView == lock) {
            if (isChecked) {
//                phoneGuardSPHelper.saveIntegerData(ConstDef.LOCK, 1);
                if (LSUtil.checkPermission(this, componentName )) {
//                    LSUtil.lockScreen(this, componentName);
                    phoneGuardSPHelper.saveIntegerData(ConstDef.LOCK, 1);
                } else {
                    LSUtil.activeManager(this, componentName);
                }

            } else {
                phoneGuardSPHelper.saveIntegerData(ConstDef.LOCK, 0);
//                setLockState(true, false);
            }
        } else if (buttonView == vibrate) {
            if (isChecked) {
//                setVibrateState(true, true);
                phoneGuardSPHelper.saveIntegerData(ConstDef.VIBRATE, 1);
            } else {
//                setVibrateState(true, false);
                phoneGuardSPHelper.saveIntegerData(ConstDef.VIBRATE, 0);
            }
        } else if (buttonView == light) {
            if (isChecked) {
                if (checkFlashlight()) {
                    showCamera();
                    phoneGuardSPHelper.saveIntegerData(ConstDef.LIGHT, 1);
                }
            } else {
                phoneGuardSPHelper.saveIntegerData(ConstDef.LIGHT, 0);
            }
        }
    }

    // 检测当前设备是否配置闪光灯
    boolean checkFlashlight() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(this, getString(R.string.no_flash_light), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void startService() {
        servicePreferenceHelper.saveIntegerData(ConstDef.SERVICE_IS_WORKING, 1);
        Intent intent = new Intent();
        intent.setClass(getBaseContext(), ProximityListenerService.class);
        startService(intent);
    }

    private void stopService() {
        servicePreferenceHelper.saveIntegerData(ConstDef.SERVICE_IS_WORKING, 0);
        boolean isRemindActivityRunning = ActivityStack.getInstance().getActivityState(AlarmActivity.class);
        if (isRemindActivityRunning) {
            ActivityStack.getInstance().popActivityByClass(AlarmActivity.class, true);
        }
        Intent intent = new Intent();
        intent.setClass(getBaseContext(), ProximityListenerService.class);
        stopService(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == about_guard_ll) {
            Intent intent = new Intent();
            intent.setClass(this, AboutGuardActivity.class);
            startActivity(intent);
        }
    }
    public void showCamera() {
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_CAMERA, mPermissionGrant);
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_RECORD_AUDIO", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_GET_ACCOUNT:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_GET_ACCOUNTS", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_READ_PHONE_STATE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_CALL_PHONE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_CAMERA:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_CAMERA", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_ACCESS_FINE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_ACCESS_COARSE_LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    Toast.makeText(getBaseContext(), "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }
}
