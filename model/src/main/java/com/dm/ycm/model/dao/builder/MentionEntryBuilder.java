package com.dm.ycm.model.dao.builder;

import com.dm.ycm.model.Entry.MentionEntry;

/**
 * Created by ycm on 2017/6/27.
 * Description:
 * Modified by:
 */

public class MentionEntryBuilder {
    private static final String TABLE_NAME = "mention";
    private static final String MENTION_ID = "_id";
    private static final String MENTION_TYPE = "type";
    private static final String MENTION_CONTENT = "content";
    private static final String MENTION_UPDATE_TIME = "update_time";
    private static final String MENTION_MENTION_TIME = "mention_time";
    private static final String[] ALL_COLUMNS = {
            MENTION_ID,
            MENTION_TYPE,
            MENTION_CONTENT,
            MENTION_UPDATE_TIME,
            MENTION_MENTION_TIME};

    public static final String SQL_CREATE_TABLE_MENTION =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + MENTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MENTION_TYPE + " INTEGER DEFAULT 0,"
                    + MENTION_CONTENT + " TEXT,"
                    + MENTION_UPDATE_TIME + " LONG DEFAULT 0,"
                    + MENTION_MENTION_TIME + " LONG DEFAULT 0)";
//                    + TYPE + " INTEGER DEFAULT 0,"
//                    + CONTENT + " TEXT,"
//                    + STATE + " INTEGER DEFAULT 0,"
//                    + SESSION_FLAG + " TEXT,"
//                    + ATTR + " INTEGER DEFAULT 0,"
//                    + LIFE_TIME + " INTEGER DEFAULT 0,"
//                    + CREATE_TIME + " INTEGER DEFAULT 0,"
//                    + SENT_TIME + " INTEGER DEFAULT 0,"
//                    + SORT_TIME + " INTEGER DEFAULT 0)";

    public static StringBuilder insertMention(MentionEntry entry) {
        int id = entry.getMention_id();
        int type = entry.getMention_type();
        String content = entry.getMention_content();
        long updateTime = entry.getUpdate_time();
        long mentionTime = entry.getMention_time();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("INSERT INTO ")
                .append(TABLE_NAME + " (")
                .append(MENTION_ID + ", ")
                .append(MENTION_TYPE + ", ")
                .append(MENTION_CONTENT + ", ")
                .append(MENTION_UPDATE_TIME + ", ")
                .append(MENTION_MENTION_TIME + ") ")
                .append("VALUE (")
                .append(id).append(", ")
                .append(type).append(", ")
                .append(content).append(", ")
                .append(updateTime).append(", ")
                .append(mentionTime).append(")");
        return stringBuilder;
    }
}
