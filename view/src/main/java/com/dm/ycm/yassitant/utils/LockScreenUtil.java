package com.dm.ycm.yassitant.utils;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by ycm on 2017/5/17.
 * Description:锁屏工具类
 */

public class LockScreenUtil {

    /**
     * 是否锁屏
     * @param context 上下文
     * @return 是否锁屏
     */
    public static boolean isScreenOn(Context context){
        boolean isScreenOn = false;
        if (context != null) {
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            isScreenOn = powerManager.isScreenOn();
        }
        return isScreenOn;
    }

}
