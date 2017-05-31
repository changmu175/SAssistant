package com.dm.ycm.yassitant.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ycm on 2017/5/12.
 * Description:
 */

public class SharePreferenceHelper{
    private Context context;
    private String name;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    /**
     * 初始化
     * @param context 上下文
     * @param name 名称
     */
    public SharePreferenceHelper(Context context, String name) {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 存储String类型数据
     * @param key 键
     * @param value 值
     */
    public void saveStringData(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**\
     * 获取String类型数据
     * @param key 键
     * @return 值
     */
    public String getStringData(String key) {
        return sharedPreferences.getString(key, null);
    }

    /**
     * 存储int类型数据
     * @param key 键
     * @param value 值
     */
    public void saveIntegerData(String key, int value) {
        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 获取int类型数据
     * @param key 键
     * @return 值
     */
    public int getIntegerData(String key) {
        return sharedPreferences.getInt(key, -1);
    }
}
