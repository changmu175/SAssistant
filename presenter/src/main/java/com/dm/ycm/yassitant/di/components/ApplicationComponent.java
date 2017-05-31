package com.dm.ycm.yassitant.di.components;

import com.dm.ycm.yassitant.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

}
