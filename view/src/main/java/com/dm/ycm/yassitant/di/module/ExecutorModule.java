package com.dm.ycm.yassitant.di.module;

import com.dm.ycm.presenter.excutor.PostExecutorThread;
import com.dm.ycm.presenter.excutor.ThreadExecutor;
import com.dm.ycm.presenter.excutor.ThreadExecutorImp;
import com.dm.ycm.presenter.excutor.UIThreadImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */
@Module
public class ExecutorModule {
    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(ThreadExecutorImp executorImp) {
        return executorImp;
    }

    @Provides
    @Singleton
    PostExecutorThread providePostExecutor(UIThreadImp uiThreadImp) {
        return uiThreadImp;
    }
}
