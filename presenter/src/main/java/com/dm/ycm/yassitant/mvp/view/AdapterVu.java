package com.dm.ycm.yassitant.mvp.view;

import android.support.annotation.NonNull;

import com.dm.ycm.yassitant.mvp.Command;
import com.dm.ycm.yassitant.mvp.Vu;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */

public interface AdapterVu<T extends Command, D> extends Vu<T> {
    /**
     * 适配器相关的View被新创建出来
     */
    void onViewCreated();

    /**
     *  适配器相关的View被重用
     */
    void onViewReused();

    /**
     * 绑定数据源到View上
     */
    void  bindDataSource(int position, @NonNull D dataSource);
}
