package com.dm.ycm.model.Entry;

/**
 * Created by ycm on 2017/6/26.
 * Description:
 * Modified by:
 */
public class MentionEntry {
    private int mention_id;
    private int mention_type;
    private String mention_content;
    private long update_time;
    private long mention_time;

    public int getMention_id() {
        return mention_id;
    }

    public void setMention_id(int mention_id) {
        this.mention_id = mention_id;
    }

    public int getMention_type() {
        return mention_type;
    }

    public void setMention_type(int mention_type) {
        this.mention_type = mention_type;
    }

    public String getMention_content() {
        return mention_content;
    }

    public void setMention_content(String mention_content) {
        this.mention_content = mention_content;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public long getMention_time() {
        return mention_time;
    }

    public void setMention_time(long mention_time) {
        this.mention_time = mention_time;
    }
}
