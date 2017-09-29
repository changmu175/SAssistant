package com.dm.ycm.yassitant.view;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dm.ycm.yassitant.command.MainCommand;
import com.dm.ycm.yassitant.R;
import com.dm.ycm.yassitant.dialog.AddMentionDialog;
import com.dm.ycm.yassitant.mvp.view.ActivitySuperVu;
import com.dm.ycm.yassitant.view.vu.MainVu;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class MainView extends ActivitySuperVu<MainCommand> implements MainVu,
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main2;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        super.init(inflater, container);
        View view = getView();
        if (view != null) {
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            getActivity().setSupportActionBar(toolbar);
            NavigationView navigationView = view.findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            DrawerLayout drawer = view.findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            FloatingActionButton fab = view.findViewById(R.id.fab);
            fab.setOnClickListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) getView().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
//        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
        AddMentionDialog initDialog = new AddMentionDialog(getContext());
        initDialog.showDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }


    @Override
    public boolean closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) getView().findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }
}
