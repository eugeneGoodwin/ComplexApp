package com.test.my.complexapp.interfaces;

import com.test.my.complexapp.retrofit.entries.JsonAlbum;
import com.test.my.complexapp.retrofit.entries.JsonComment;
import com.test.my.complexapp.retrofit.entries.JsonPhoto;
import com.test.my.complexapp.retrofit.entries.JsonPost;
import com.test.my.complexapp.retrofit.entries.JsonUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Android-dev on 22.08.2016.
 */
public interface API {
    @GET("/posts/")
    Observable<List<JsonPost>> getPosts();

    @GET("/comments/")
    Observable<List<JsonComment>> getComments();

    @GET("/albums/")
    Observable<List<JsonAlbum>> getAlbums();

    @GET("/photos/")
    Observable<List<JsonPhoto>> getPhotos();

    @GET("/users/")
    Observable<List<JsonUser>> getUsers();

    @GET("/users/")
    Observable<Response<List<JsonUser>>> getTestUsers();
}
