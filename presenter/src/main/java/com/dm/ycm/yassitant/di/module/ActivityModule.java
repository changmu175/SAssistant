package com.dm.ycm.yassitant.di.module;

import android.app.Activity;
import android.content.Context;

import com.dm.ycm.yassitant.di.components.DiConfig;
import com.dm.ycm.yassitant.di.components.annotation.PerActivity;
import com.dm.ycm.yassitant.di.components.annotation.Scoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }

    @Provides
    @PerActivity
    @Scoped(DiConfig.CONTEXT_SCOPE_ACTIVITY)
    Context provideContext(){
        return this.activity;
    }
}
