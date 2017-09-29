package com.dm.ycm.presenter.interactor.def;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public interface Interactor<T> {
    Interactor<T> get();

    void execute(Subscriber<T> useCaseSubscriber);

    void execute(Action1<T> action1);

    void unSubscribe();
}
