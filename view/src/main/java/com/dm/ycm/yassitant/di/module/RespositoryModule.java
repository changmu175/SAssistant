package com.dm.ycm.yassitant.di.module;

import android.content.Context;

import com.dm.ycm.model.Client;
import com.dm.ycm.presenter.repository.MentionProxy;
import com.dm.ycm.presenter.repository.MentionProxyRepository;
import com.dm.ycm.yassitant.di.components.annotation.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */
@Module
public class RespositoryModule {
    @Provides
    @PerActivity
    MentionProxyRepository provideMentionProxyRepository(MentionProxy mentionProxy) {
        return mentionProxy;
    }

    @Provides
    @PerActivity
    Client provideClient(Context context) {
        return Client.getInstance(context);
    }
}
