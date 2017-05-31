package com.dm.ycm.yassitant.mvp.view.vu;

import android.support.annotation.StringRes;
import android.view.Menu;
import android.view.MenuItem;

import com.dm.ycm.yassitant.mvp.Command;
import com.dm.ycm.yassitant.mvp.Vu;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */

public interface ActivityVu<T extends Command> extends Vu<T> {

    void onCreated();

    void onResume();

    void onStart();

    void onRestart();

    void onPause();

    void onStop();

    void onDestroy();

    void onAttachedToWindow();

    void onDetachedFromWindow();

    boolean onCreateOptionsMenu(Menu menu);

    boolean onOptionsItemSelected(MenuItem item);

    boolean onPrepareOptionsMenu(Menu menu);

    void showCommonProgressDialog(String msg);

    void showProgressDialog();

    void showCommonProgressDialog(@StringRes int resId);

    void showCommonProgressDialog(String msg, boolean onKey);

    void dismissCommonProgressDialog();

    void showToast(String msg);

    void showToast(@StringRes int resId);

}
