package com.dm.ycm.yassitant.mvp.presenter;

import android.os.Bundle;

import com.dm.ycm.yassitant.MyApplication;
import com.dm.ycm.yassitant.di.components.DaggerUseCaseComponent;
import com.dm.ycm.yassitant.di.components.UseCaseComponent;
import com.dm.ycm.yassitant.di.module.MentionUseCaseModule;
import com.dm.ycm.yassitant.mvp.Command;
import com.dm.ycm.yassitant.mvp.view.vu.ActivityVu;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public abstract class AbstractMentionActivity<P extends Command, V extends ActivityVu>
        extends BasePresenterActivity<P,V>{
    protected UseCaseComponent useCaseComponent;

    @Override
    protected void preBindView(Bundle savedInstanceState) {
        super.preBindView(savedInstanceState);
//        ApplicationComponent applicationComponent = MyApplication.myApplication.getComponent();
////        useCaseComponent = DaggerUseCaseComponent.builder().
        useCaseComponent = DaggerUseCaseComponent
                .builder()
                .mentionUseCaseModule(new MentionUseCaseModule())
                .applicationComponent(MyApplication.myApplication.getComponent()).build();

//        useCaseComponent = DaggerUseCaseComponent
//                .builder()
//                .mentionUseCaseModule(new MentionUseCaseModule())
//                .build();
    }
}
