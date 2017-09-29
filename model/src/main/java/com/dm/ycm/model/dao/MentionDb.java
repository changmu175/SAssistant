package com.dm.ycm.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dm.ycm.model.Entry.MentionEntry;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class MentionDb {
    private static final String DATABASE_NAME = "mention.db";
    private static MentionDb instance;
    private SQLiteDatabase sqLiteDatabase;
    private MentionSqLiteHelper mentionSqliteHelper;

    public static MentionDb getInstance() {
        if (instance == null) {
            synchronized (MentionDb.class) {
                if (instance == null) {
                    instance = new MentionDb();
                }
            }
        }
        return instance;
    }

    private MentionDb() {
    }

    public void initDatabase(Context context) {
        if (context == null) {
            throw new RuntimeException("database init exception...");
        }

        mentionSqliteHelper = new MentionSqLiteHelper(context, DATABASE_NAME);
    }

    public synchronized SQLiteDatabase getDatabase() {
        if (mentionSqliteHelper == null) {
            throw new RuntimeException("database already closed...");
        }

        if (sqLiteDatabase == null) {
            sqLiteDatabase = mentionSqliteHelper.getReadableDatabase();
        }
        return sqLiteDatabase;
    }

    public synchronized void close() {
        if (instance != null && mentionSqliteHelper != null) {
            mentionSqliteHelper.close();
        }
        mentionSqliteHelper = null;
        instance = null;
    }

    public void insert(MentionEntry entry) {
        mentionSqliteHelper.insertMention(getDatabase(), entry);
    }
}
