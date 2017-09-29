package com.dm.ycm.presenter.interactor.usecase;

import com.dm.ycm.presenter.excutor.PostExecutorThread;
import com.dm.ycm.presenter.excutor.ThreadExecutor;
import com.dm.ycm.presenter.interactor.def.InitProxy;
import com.dm.ycm.presenter.interactor.def.Interactor;
import com.dm.ycm.presenter.repository.MentionProxyRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class InitMentionUseCase extends MentionUseCase<Integer> implements InitProxy {

    @Inject
    protected InitMentionUseCase(ThreadExecutor threadExecutor,
                                 PostExecutorThread postExecutorThread,
                                 MentionProxyRepository mentionProxyRepository) {
        super(threadExecutor, postExecutorThread, mentionProxyRepository);
    }

    @Override
    public Interactor<Integer> get() {
        return this;
    }


    @Override
    public Observable<Integer> buildUseCaseObservable() {
        return mentionProxyRepository.initMentionProxy();
    }
}
