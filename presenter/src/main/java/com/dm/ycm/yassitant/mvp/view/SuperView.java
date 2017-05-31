package com.dm.ycm.yassitant.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dm.ycm.yassitant.mvp.Command;
import com.dm.ycm.yassitant.mvp.Vu;
import com.dm.ycm.yassitant.mvp.annotation.ContentView;

import butterknife.ButterKnife;

/**
 * Created by ycm on 2017/5/20.
 * Description:
 * Modified by:
 */

public class SuperView<T extends Command> implements Vu<T> {
    private View view;
    private T command;
    private Activity activity;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        ContentView contentView = getClass().getAnnotation(ContentView.class);
        if (contentView != null)
            view = inflater.inflate(contentView.value(), container, false);
        else
            view = inflater.inflate(getLayoutRes(),container,false);
        injectView();
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setCommand(T command) {
        this.command = command;
    }

    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return this.activity;
    }

    protected void injectView() {
        ButterKnife.bind(this, view);
    }

    @LayoutRes
    protected int getLayoutRes(){
        return -1;
    }

    public Context getContext() {
        return this.activity;
    }
    /**
     * 获取String字符串
     *
     * @param res 字符串资源ID
     * @return 目标字符串
     */
    @NonNull
    public String getStringRes(@StringRes int res) {
        return getContext().getString(res);
    }

    /**
     * 获取Color颜色
     *
     * @param res 颜色资源ID
     * @return 目标颜色
     */
    public int getColorRes(@ColorRes int res) {
        return getContext().getResources().getColor(res);
    }

    /**
     * 获取Drawable
     *
     * @param res 图片资源ID
     * @return 目标Drawable
     */
    @Nullable
    public Drawable getDrawableRes(@DrawableRes int res) {
        return getContext().getResources().getDrawable(res);
    }
}
