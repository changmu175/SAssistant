package com.dm.ycm.yassitant.di.components;

import com.dm.ycm.model.Client;
import com.dm.ycm.presenter.repository.MentionProxyRepository;
import com.dm.ycm.yassitant.activity.Main2Activity;
import com.dm.ycm.yassitant.di.components.annotation.PerActivity;
import com.dm.ycm.yassitant.di.module.MentionUseCaseModule;
import com.dm.ycm.yassitant.di.module.RespositoryModule;

import dagger.Component;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class},
        modules = {MentionUseCaseModule.class,
                RespositoryModule.class})
public interface UseCaseComponent extends ApplicationComponent{
    void inject(Main2Activity main2Activity);

    MentionProxyRepository mentionProxyRepository();

    Client client();

}
