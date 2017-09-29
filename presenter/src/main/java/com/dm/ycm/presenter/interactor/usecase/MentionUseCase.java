package com.dm.ycm.presenter.interactor.usecase;

import com.dm.ycm.presenter.excutor.PostExecutorThread;
import com.dm.ycm.presenter.excutor.ThreadExecutor;
import com.dm.ycm.presenter.interactor.UseCase;
import com.dm.ycm.presenter.repository.MentionProxyRepository;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public abstract class MentionUseCase<T> extends UseCase<T> {
    protected MentionProxyRepository mentionProxyRepository;

    protected MentionUseCase(ThreadExecutor threadExecutor,
                             PostExecutorThread postExecutorThread,
                             MentionProxyRepository mentionProxyRepository) {
        super(threadExecutor, postExecutorThread);

        this.mentionProxyRepository = mentionProxyRepository;
    }
}
