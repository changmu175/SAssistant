package com.dm.ycm.presenter.interactor;

import com.dm.ycm.presenter.excutor.PostExecutorThread;
import com.dm.ycm.presenter.excutor.ThreadExecutor;
import com.dm.ycm.presenter.interactor.def.Interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public abstract class UseCase<T> implements Interactor<T>{
    private ThreadExecutor executor;

    private PostExecutorThread mainThread;

    private Subscription subscription = Subscriptions.empty();

    protected UseCase(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread) {
        this.executor = threadExecutor;
        this.mainThread = postExecutorThread;
    }

    public abstract Observable<T> buildUseCaseObservable();

    @Override
    public void execute(Subscriber<T> useCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(executor))
                .observeOn(mainThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    @Override
    public void execute(Action1<T> action1) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(executor))
                .observeOn(mainThread.getScheduler())
                .subscribe(action1);
    }

    @Override
    public void unSubscribe() {
        if (!this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
    }
}
