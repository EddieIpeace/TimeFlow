package com.example.timeflow;

import org.litepal.crud.DataSupport;

public class Event extends DataSupport {
    private int id;
    private long begin_ts;
    private long end_ts;
    private String desc;

    public Event(long begin_ts, long end_ts, String desc) {
        this.id = 0;
        this.begin_ts = begin_ts;
        this.end_ts = end_ts;
        this.desc = desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBegin_ts(long begin_ts) {
        this.begin_ts = begin_ts;
    }

    public void setEnd_ts(long end_ts) {
        this.end_ts = end_ts;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public long getBegin_ts() {
        return begin_ts;
    }

    public long getEnd_ts() {
        return end_ts;
    }

    public String getDesc() {
        return desc;
    }

}
