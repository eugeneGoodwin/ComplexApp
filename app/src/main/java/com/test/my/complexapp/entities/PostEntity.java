package com.test.my.complexapp.entities;

import com.test.my.complexapp.retrofit.entries.JsonPost;

import java.util.List;

public class PostEntity {
    private List<JsonPost> list;
    private long timestamp;

    public PostEntity(List<JsonPost> list, long timestamp){
        this.list = list;
        this.timestamp = timestamp;
    }

    public List<JsonPost> getList() {
        return list;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
