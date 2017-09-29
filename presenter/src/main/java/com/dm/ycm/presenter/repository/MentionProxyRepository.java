package com.dm.ycm.presenter.repository;

import rx.Observable;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public interface MentionProxyRepository {

    Observable<Integer> initMentionProxy();

    Observable<Integer> releaseMentionProxy();
}
