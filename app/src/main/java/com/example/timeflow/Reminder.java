package com.example.timeflow;

import org.litepal.crud.DataSupport;

public class Reminder extends DataSupport {
    private int id;
    private String desc;
    private long remind_ts;
    private int status;

    public Reminder(String desc) {
        id = 0;
        remind_ts = 0;
        status = 0;
        this.desc = desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRemind_ts(long remind_ts) {
        this.remind_ts = remind_ts;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public long getRemind_ts() {
        return remind_ts;
    }

    public int getStatus() {
        return status;
    }

}
