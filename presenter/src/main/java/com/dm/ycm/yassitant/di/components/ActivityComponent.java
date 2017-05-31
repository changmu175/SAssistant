package com.dm.ycm.yassitant.di.components;

import android.app.Activity;
import android.content.Context;

import com.dm.ycm.yassitant.di.components.annotation.PerActivity;
import com.dm.ycm.yassitant.di.components.annotation.Scoped;
import com.dm.ycm.yassitant.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity activity();
    @Scoped(DiConfig.CONTEXT_SCOPE_ACTIVITY)
    Context context();
}
