package com.dm.ycm.yassitant.mvp.activityStack;

import android.app.Activity;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by ycm on 2017/5/21.
 * Description:
 * Modified by:
 */

public class ActivityStack {
    private static ActivityStack instance;
    private static LinkedList<Activity> activities;
    private HashMap<Activity, Boolean> activityStateMap;

    //单例模式
    public static ActivityStack getInstance(){
        if (instance == null) {
            synchronized (ActivityStack.class) {
                if (instance == null) {
                    instance = new ActivityStack();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化
     */
    private ActivityStack() {
        activities = new LinkedList<>();
        activityStateMap = new HashMap<>();
    }

    /**
     * 设置activity是否正在运行状态
     * @param activity 上下文
     * @param isRunning 是否正在运行
     */
    public void putActivityState(Activity activity, boolean isRunning) {
        activityStateMap.put(activity, isRunning);
    }

    /**
     * 获取activity状态
     * @param cs 类
     * @return 是否正在运行
     */
    public boolean getActivityState(Class<? extends Activity> cs) {
        Activity activity = getActivityByClass(cs);
        return getActivityState(activity);
    }

    /**
     * 获取activity状态
     * @param activity activity
     * @return 是否正在运行
     */
    private boolean getActivityState(Activity activity) {
        Boolean isRunning = activityStateMap.get(activity);
        if (isRunning == null) {
            return false;
        } else {
            return isRunning;
        }
    }

    /**
     * 把activity置入栈中
     * @param activity activity
     */
    public void pushActivity(Activity activity) {
        activities.addFirst(activity);
        putActivityState(activity, true);
    }

    /**
     * 弹出activity
     * @param cs 类名
     * @param isFinish 是否结束
     * @return
     */
    public  boolean popActivityByClass(Class<? extends Activity> cs, boolean isFinish) {
        return popActivity(getActivityByClass(cs), isFinish);
    }

    /**
     * 弹出activity
     * @param activity activity
     * @param isFinish 是否结束
     * @return
     */
    public boolean popActivity(Activity activity, boolean isFinish) {
        if (activities.isEmpty() || activity == null) {
            return false;
        }

        if (activities.contains(activity)) {
            if (isFinish) {
                activity.finish();
            }
            return activities.remove(activity);
        }
        putActivityState(activity, false);
        return false;
    }

    /**
     * 根据类名获取activity
     * @param cs 类名
     * @return activity
     */
    private  Activity getActivityByClass(Class<? extends Activity> cs) {
        for (Activity activity : activities) {
            if (activity.getClass().equals(cs)) {
                return activity;
            }
        }
        return null;
    }
}
