package com.dm.ycm.presenter.excutor;

import javax.inject.Inject;

import dagger.Module;
import rx.Scheduler;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */
public class UIThreadImp implements PostExecutorThread {
    @Inject
    public UIThreadImp() {

    }
    @Override
    public Scheduler getScheduler() {
        return null;
    }
}
