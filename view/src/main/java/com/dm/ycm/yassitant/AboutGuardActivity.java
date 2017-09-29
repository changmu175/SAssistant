package com.dm.ycm.yassitant;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ycm on 2017/5/30.
 * Description:
 * Modified by:
 */

public class AboutGuardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_guard);
        Looper.loop();
    }
}
