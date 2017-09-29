package com.dm.ycm.model;

import android.content.Context;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class Client {
    private static Client instance;
    private Context context;

    public static Client getInstance(Context context) {
        if (instance == null) {
            synchronized (Client.class) {
                if (instance == null) {
                    instance = new Client(context);
                }
            }
        }
        return instance;
    }

    private Client(Context context) {
        this.context = context;
    }

    public void initMention(Context context){
//        MentionDb.getInstance().initDatabase(context);
    }
}
