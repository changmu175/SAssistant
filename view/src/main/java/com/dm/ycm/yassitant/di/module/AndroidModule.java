package com.dm.ycm.yassitant.di.module;

import android.content.Context;

import com.dm.ycm.yassitant.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */
@Module
public class AndroidModule {
    private final MyApplication application;

    public AndroidModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context ApplicationContext() {
        return application;
    }

}
