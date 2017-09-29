package com.dm.ycm.yassitant.mvp.view;

import android.support.annotation.StringRes;
import android.view.Menu;
import android.view.MenuItem;

import com.dm.ycm.yassitant.mvp.Command;
import com.dm.ycm.yassitant.mvp.view.vu.ActivityVu;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */

public class ActivitySuperVu<T extends Command> extends SuperView<T> implements ActivityVu<T> {

    @Override
    public void onCreated() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public void onDetachedFromWindow() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void showCommonProgressDialog(String msg) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void showCommonProgressDialog(@StringRes int resId) {

    }

    @Override
    public void showCommonProgressDialog(String msg, boolean onKey) {

    }

    @Override
    public void dismissCommonProgressDialog() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showToast(@StringRes int resId) {

    }
}
