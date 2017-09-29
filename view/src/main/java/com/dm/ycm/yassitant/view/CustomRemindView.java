package com.dm.ycm.yassitant.view;

import android.view.View;

import com.dm.ycm.yassitant.R;
import com.dm.ycm.yassitant.command.CustomRemindCommand;
import com.dm.ycm.yassitant.mvp.view.ActivitySuperVu;
import com.dm.ycm.yassitant.view.vu.CustomRemindVu;

/**
 * Created by ycm on 2017/7/16.
 * Description:
 * Modified by:
 */

public class CustomRemindView extends ActivitySuperVu<CustomRemindCommand>
        implements CustomRemindVu, View.OnClickListener {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main2;
    }

    @Override
    public void onClick(View view) {

    }
}
