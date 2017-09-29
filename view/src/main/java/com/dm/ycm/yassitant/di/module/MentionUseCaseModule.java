package com.dm.ycm.yassitant.di.module;

import com.dm.ycm.presenter.interactor.def.InitProxy;
import com.dm.ycm.presenter.interactor.usecase.InitMentionUseCase;
import com.dm.ycm.yassitant.di.components.annotation.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */
@Module
public class MentionUseCaseModule {
    @Provides
    @PerActivity
    InitProxy provideInitProxy(InitMentionUseCase useCase) {
        return useCase;
    }
}
