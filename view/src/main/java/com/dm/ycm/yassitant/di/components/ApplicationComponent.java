package com.dm.ycm.yassitant.di.components;

import android.content.Context;

import com.dm.ycm.presenter.excutor.PostExecutorThread;
import com.dm.ycm.presenter.excutor.ThreadExecutor;
import com.dm.ycm.presenter.repository.MentionProxyRepository;
import com.dm.ycm.yassitant.MyApplication;
import com.dm.ycm.yassitant.di.components.annotation.Scoped;
import com.dm.ycm.yassitant.di.module.AndroidModule;
import com.dm.ycm.yassitant.di.module.ExecutorModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        ExecutorModule.class
})
public interface ApplicationComponent {
    void inject(MyApplication application);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutorThread postExecutorThread();
}
