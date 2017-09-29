package com.dm.ycm.yassitant.mvp.view;

import android.support.annotation.NonNull;

import com.dm.ycm.yassitant.mvp.Command;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */

public class AdapterSuperVu<T extends Command, D> extends SuperView<T> implements AdapterVu<T,D> {
    protected D dataSource;

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onViewReused() {

    }

    @Override
    public void bindDataSource(int position, @NonNull D dataSource) {
        this.dataSource = dataSource;
    }
}
