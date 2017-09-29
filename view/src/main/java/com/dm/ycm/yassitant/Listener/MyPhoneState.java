package com.dm.ycm.yassitant.Listener;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.dm.ycm.yassitant.callback.LTCallback;

/**
 * Created by ycm on 2017/5/17.
 *Description: 监听电话状态
 */

class MyPhoneState extends PhoneStateListener  {
    private LTCallback.RemindCallback remindCallback;

    MyPhoneState() {
        this.remindCallback = new LTCallback.RemindCallback();
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                //电话为空闲状态
                ProximitySensorEventListener.setIsCalling(false);
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                //响铃中，不提在提醒
                if (remindCallback != null) {
                    remindCallback.stopRemind();
                }
                //响铃中，设置为正在通话
                ProximitySensorEventListener.setIsCalling(true);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                //通话中，不提在提醒
                if (remindCallback != null) {
                    remindCallback.stopRemind();
                }
                //通话中，设置为正在通话
                ProximitySensorEventListener.setIsCalling(true);
                break;
            default:
                break;
        }
    }
}
