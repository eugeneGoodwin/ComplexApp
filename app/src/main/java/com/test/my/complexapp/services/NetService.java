package com.test.my.complexapp.services;


import com.test.my.complexapp.interfaces.API;
import com.test.my.complexapp.retrofit.entries.JsonPost;
import com.test.my.complexapp.retrofit.entries.JsonUser;

import java.util.List;
import java.util.Observable;

import retrofit2.Retrofit;

public class NetService {
    Retrofit retrofit;
    private static API api;

    public static API getApi() {
        return api;
    }

    public NetService(Retrofit retrofit){
        this.retrofit = retrofit;
        api = this.retrofit.create(API.class);
    }

    public rx.Observable<List<JsonPost>> getPosts() {
        return getApi().getPosts();
    }

    public rx.Observable<List<JsonUser>> getUsers(){
        return getApi().getUsers();
    }
}
