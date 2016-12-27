package com.test.my.complexapp.models;

import com.test.my.complexapp.entities.PostEntity;
import com.test.my.complexapp.services.NetService;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

public class PostModel {
    private static final long STALE_MS = 10 * 1000;

    private final NetService networkService;

    private PostEntity memoryCache = null;
    private ReplaySubject<PostEntity> helloSubject;
    private Subscription helloSubscription;

    public PostModel(NetService networkService){
        this.networkService = networkService;
    }

    public Observable<PostEntity> value() {
        if (helloSubscription == null || helloSubscription.isUnsubscribed()) {
            helloSubject = ReplaySubject.create();

            helloSubscription = Observable.concat(memory(), network())
                    .subscribeOn(Schedulers.newThread())
                    .first(entity -> entity != null && isUpToDate(entity))
                    .subscribe(helloSubject);
        }

        return helloSubject.asObservable();
    }

    private Observable<PostEntity> network() {
        return networkService.getPosts()
                .subscribeOn(Schedulers.newThread())
                .map(data -> new PostEntity(data, System.currentTimeMillis()))
                .doOnNext(entity -> {
                    memoryCache = entity;
                    System.out.println("network thread: " + Thread.currentThread().getId());
                });
    }

    private Observable<PostEntity> memory() {
        return Observable.just(memoryCache);
    }

    public void clearMemoryCache() {
        memoryCache = null;
    }

    private boolean isUpToDate(PostEntity entity) {
        return System.currentTimeMillis() - entity.getTimestamp() < STALE_MS;
    }
}
