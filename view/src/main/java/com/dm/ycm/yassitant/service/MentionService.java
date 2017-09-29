package com.dm.ycm.yassitant.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class MentionService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initDatabase();
        return super.onStartCommand(intent, flags, startId);
    }

    private void initDatabase() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
