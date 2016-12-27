package com.test.my.complexapp.models;


import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.test.my.complexapp.entities.User;
import com.test.my.complexapp.entities.tables.UsersTable;
import com.test.my.complexapp.retrofit.entries.JsonUser;
import com.test.my.complexapp.services.NetService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class UserModel {

    private final NetService networkService;
    private StorIOSQLite storIOSQLite;

    private PublishSubject<List<User>> mSubject = PublishSubject.create();


    public UserModel(NetService networkService, StorIOSQLite storIOSQLite){
        this.networkService = networkService;
        this.storIOSQLite = storIOSQLite;
    }

    public Observable<List<User>> getObservable() {
        Observable<List<User>> firstTimeObservable =
                Observable.fromCallable(this::getAllUsersFromDb);
        return firstTimeObservable.concatWith(mSubject);
    }

    private List<User> getAllUsersFromDb() {
        List<User> list = null;
        list = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(UsersTable.QUERY_ALL)
                .prepare()
                .asRxObservable()
                .observeOn(Schedulers.io())
                .toBlocking()
                .first();
        return list;
    }

    private Observable<List<JsonUser>> network() {
        return networkService.getUsers()
                .subscribeOn(Schedulers.io());
    }

    public Observable<String> updateUsers() {
        BehaviorSubject<String> requestSubject = BehaviorSubject.create();
        network()
                .observeOn(Schedulers.io())
                .subscribe(l -> {
                            addUsers(l);
                            requestSubject.onNext("OK");
                            },
                        e -> requestSubject.onError(e),
                        () -> requestSubject.onCompleted());
        return requestSubject.asObservable();
    }

    public void addUsers(List<JsonUser> jsonUsers){
        final List<User> users = new ArrayList<User>();

        for(JsonUser user : jsonUsers){
            users.add(User.newUser(user.getName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getWebsite()));
        }

        storIOSQLite
                .put()
                .objects(users)
                .prepare()
                .asRxObservable()
                .observeOn(Schedulers.io())
                .subscribe(result -> mSubject.onNext(users),
                            err -> mSubject.onError(err));
    }

    public void addUser(JsonUser jsonUser){
        final User user = User.newUser(jsonUser.getName(), jsonUser.getUsername(), jsonUser.getEmail(), jsonUser.getPhone(), jsonUser.getWebsite());

        storIOSQLite
                .put()
                .object(user)
                .prepare()
                .asRxObservable()
                .observeOn(Schedulers.io())
                .subscribe(result -> {
                            List<User> users = new ArrayList<User>();
                            if(result.wasInserted()) {
                                users.add(user);
                                mSubject.onNext(users);
                            }
                        },
                        err -> mSubject.onError(err));
    }
}
