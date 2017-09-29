package com.dm.ycm.presenter.repository;

import android.content.Context;

import com.dm.ycm.model.Client;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class MentionProxy implements MentionProxyRepository {
    Client client;
    Context context;
    @Inject
    public MentionProxy(Client client,
            Context context) {
        this.client = client;
        this.context = context;
    }

    @Override
    public Observable<Integer> initMentionProxy() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                client.initMention(context);
            }
        });
    }

    @Override
    public Observable<Integer> releaseMentionProxy() {
        return null;
    }
}
