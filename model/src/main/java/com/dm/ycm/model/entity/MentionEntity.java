package com.dm.ycm.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ycm on 2017/6/26.
 * Description:
 * Modified by:
 */
@Entity
public class MentionEntity {
    @Id(autoincrement = true)
    private Long mention_id;
    private int mention_type;
    private String mention_content;
    private long update_time;
    private long mention_time;
    @Generated(hash = 1498529685)
    public MentionEntity(Long mention_id, int mention_type, String mention_content,
            long update_time, long mention_time) {
        this.mention_id = mention_id;
        this.mention_type = mention_type;
        this.mention_content = mention_content;
        this.update_time = update_time;
        this.mention_time = mention_time;
    }
    @Generated(hash = 110062508)
    public MentionEntity() {
    }
    public Long getMention_id() {
        return this.mention_id;
    }
    public void setMention_id(Long mention_id) {
        this.mention_id = mention_id;
    }
    public int getMention_type() {
        return this.mention_type;
    }
    public void setMention_type(int mention_type) {
        this.mention_type = mention_type;
    }
    public String getMention_content() {
        return this.mention_content;
    }
    public void setMention_content(String mention_content) {
        this.mention_content = mention_content;
    }
    public long getUpdate_time() {
        return this.update_time;
    }
    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }
    public long getMention_time() {
        return this.mention_time;
    }
    public void setMention_time(long mention_time) {
        this.mention_time = mention_time;
    }
}
