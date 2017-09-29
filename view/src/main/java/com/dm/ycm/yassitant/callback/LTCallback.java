package com.dm.ycm.yassitant.callback;

import com.dm.ycm.yassitant.dialog.AlarmActivity;
import com.dm.ycm.yassitant.utils.ActivityStack;

/**
 * Created by ycm on 2017/5/18.
 *
 */

public class LTCallback {
    public static class RemindCallback{
        public void stopRemind() {
            boolean isRemind = ActivityStack.getInstance().getActivityState(AlarmActivity.class);
            if (isRemind) {
                ActivityStack.getInstance().popActivityByClass(AlarmActivity.class, true);
            }
        }
    }
}
