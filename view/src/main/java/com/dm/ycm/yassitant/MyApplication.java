package com.dm.ycm.yassitant;

import android.app.Application;

import com.dm.ycm.yassitant.di.components.ApplicationComponent;
import com.dm.ycm.yassitant.di.components.DaggerApplicationComponent;
import com.dm.ycm.yassitant.di.module.AndroidModule;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class MyApplication extends Application {
    private ApplicationComponent component;
    public static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        component = DaggerApplicationComponent.builder().androidModule(new AndroidModule(this)).build();
        getComponent().inject(this);
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
