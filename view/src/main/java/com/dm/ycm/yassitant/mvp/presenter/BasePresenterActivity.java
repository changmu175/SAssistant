package com.dm.ycm.yassitant.mvp.presenter;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.dm.ycm.yassitant.mvp.Command;
import com.dm.ycm.yassitant.mvp.activityStack.ActivityStack;
import com.dm.ycm.yassitant.mvp.annotation.StackInto;
import com.dm.ycm.yassitant.mvp.view.vu.ActivityVu;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */

public abstract class BasePresenterActivity<P extends Command, V extends ActivityVu> extends AppCompatActivity {
    private V vu;

    public V getVu() {
        return vu;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StackInto annotation = getClass().getAnnotation(StackInto.class);
        if (annotation == null || annotation.value()) {
            ActivityStack.getInstance().pushActivity(this);
        }
        preBindView(savedInstanceState);
        try {
            if (getVuClass() != null) {
                vu = getVuClass().newInstance();
                vu.setCommand(getCommand());
                vu.setActivity(this);

                vu.init(getLayoutInflater(), null);
                setContentView(vu.getView());
                vu.onCreated();
            }
            onBindView(savedInstanceState);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        ActivityStack.getInstance().popActivity(this, false);
        if (vu != null) {
            vu.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (vu != null) {
            vu.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (vu != null) {
            vu.onResume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (vu != null) {
            vu.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (vu != null) {
            vu.onResume();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (vu != null) vu.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (vu != null) vu.onDetachedFromWindow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (vu != null) vu.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (vu != null) vu.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (vu != null) vu.onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public Resources getResources() {
        Resources res=super.getResources();
        Configuration config = res.getConfiguration();
        config.fontScale = 1.0f;
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }

    @NonNull
    protected abstract Class<? extends V> getVuClass();

    @NonNull
    protected abstract P getCommand();

    protected void preBindView(Bundle savedInstanceState) {

    }

    protected void onBindView(Bundle savedInstanceState) {

    }
}
