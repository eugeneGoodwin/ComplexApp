package com.test.my.complexapp.services;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.test.my.complexapp.retrofit.entries.JsonUser;

import java.util.List;

import rx.subjects.PublishSubject;

public class DBService {

    StorIOSQLite storIOSQLite;
    private PublishSubject<List<JsonUser>> subject = PublishSubject.create();

    public DBService(StorIOSQLite storIOSQLite){
        this.storIOSQLite = storIOSQLite;
    }


}
