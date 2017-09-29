package com.dm.ycm.model.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.dm.ycm.model.entity.MentionEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MENTION_ENTITY".
*/
public class MentionEntityDao extends AbstractDao<MentionEntity, Long> {

    public static final String TABLENAME = "MENTION_ENTITY";

    /**
     * Properties of entity MentionEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Mention_id = new Property(0, Long.class, "mention_id", true, "_id");
        public final static Property Mention_type = new Property(1, int.class, "mention_type", false, "MENTION_TYPE");
        public final static Property Mention_content = new Property(2, String.class, "mention_content", false, "MENTION_CONTENT");
        public final static Property Update_time = new Property(3, long.class, "update_time", false, "UPDATE_TIME");
        public final static Property Mention_time = new Property(4, long.class, "mention_time", false, "MENTION_TIME");
    }


    public MentionEntityDao(DaoConfig config) {
        super(config);
    }
    
    public MentionEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MENTION_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: mention_id
                "\"MENTION_TYPE\" INTEGER NOT NULL ," + // 1: mention_type
                "\"MENTION_CONTENT\" TEXT," + // 2: mention_content
                "\"UPDATE_TIME\" INTEGER NOT NULL ," + // 3: update_time
                "\"MENTION_TIME\" INTEGER NOT NULL );"); // 4: mention_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MENTION_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MentionEntity entity) {
        stmt.clearBindings();
 
        Long mention_id = entity.getMention_id();
        if (mention_id != null) {
            stmt.bindLong(1, mention_id);
        }
        stmt.bindLong(2, entity.getMention_type());
 
        String mention_content = entity.getMention_content();
        if (mention_content != null) {
            stmt.bindString(3, mention_content);
        }
        stmt.bindLong(4, entity.getUpdate_time());
        stmt.bindLong(5, entity.getMention_time());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MentionEntity entity) {
        stmt.clearBindings();
 
        Long mention_id = entity.getMention_id();
        if (mention_id != null) {
            stmt.bindLong(1, mention_id);
        }
        stmt.bindLong(2, entity.getMention_type());
 
        String mention_content = entity.getMention_content();
        if (mention_content != null) {
            stmt.bindString(3, mention_content);
        }
        stmt.bindLong(4, entity.getUpdate_time());
        stmt.bindLong(5, entity.getMention_time());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MentionEntity readEntity(Cursor cursor, int offset) {
        MentionEntity entity = new MentionEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // mention_id
            cursor.getInt(offset + 1), // mention_type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // mention_content
            cursor.getLong(offset + 3), // update_time
            cursor.getLong(offset + 4) // mention_time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MentionEntity entity, int offset) {
        entity.setMention_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMention_type(cursor.getInt(offset + 1));
        entity.setMention_content(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUpdate_time(cursor.getLong(offset + 3));
        entity.setMention_time(cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MentionEntity entity, long rowId) {
        entity.setMention_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MentionEntity entity) {
        if(entity != null) {
            return entity.getMention_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MentionEntity entity) {
        return entity.getMention_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}