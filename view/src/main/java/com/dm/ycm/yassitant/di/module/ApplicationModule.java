//package com.dm.ycm.yassitant.di.module;
//
//import android.app.Application;
//import android.content.Context;
//
//
//import com.dm.ycm.yassitant.di.components.DiConfig;
//import com.dm.ycm.yassitant.di.components.annotation.Scoped;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//
///**
// * Created by ycm on 2017/5/21.
// * Description:
// * Modified by:
// */
//@Module
//public class ApplicationModule {
//
//    private final Application application;
//
//    public ApplicationModule(Application application) {
//        this.application = application;
//    }
//
//    @Provides
//    @Singleton
//    @Scoped(DiConfig.CONTEXT_SCOPE_APP)
//    Context provideApplicationContext() {
//        return this.application;
//    }
//}
