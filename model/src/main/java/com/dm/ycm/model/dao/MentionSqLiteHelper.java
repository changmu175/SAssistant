package com.dm.ycm.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dm.ycm.model.Entry.MentionEntry;
import com.dm.ycm.model.dao.builder.MentionEntryBuilder;

import java.util.List;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class MentionSqLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static MentionSqLiteHelper instance;

    public MentionSqLiteHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MentionEntryBuilder.SQL_CREATE_TABLE_MENTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mention");
        onCreate(sqLiteDatabase);
    }

    public void insertMention(SQLiteDatabase sqLiteDatabase, MentionEntry entry) {
        sqLiteDatabase.execSQL(MentionEntryBuilder.insertMention(entry).toString());
    }

//    public List<MentionEntry> getMention() {
//
//    }
}
