package com.test.my.complexapp.presenters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.test.my.complexapp.Application;
import com.test.my.complexapp.adapters.RecycleViewAdapter;
import com.test.my.complexapp.models.PostModel;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

public class MainPresenter extends BaseProgressPresenter<MainPresenter.View>{

    @Inject
    PostModel postModel;

    RecycleViewAdapter postAdapter;

    private Subscription subscription = Subscriptions.empty();

    public MainPresenter(){
    }

    public void init(){

        Application.getApplication().getAppComponent().inject(this);

        RecyclerView recyclerView = view.getRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        postAdapter = new RecycleViewAdapter(null);
        recyclerView.setAdapter(postAdapter);
        update();
    }

    public void update(){
        showProgress();
        subscription = postModel
                .value()
                //.concatMap(postModel -> rx.Observable.from(postModel.getList()))
                //.toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(entity -> System.out.println("value thread: " + Thread.currentThread().getId()))
                .subscribe(postModel -> {
                            hideProgress();
                            setClock(postModel.getTimestamp());
                            postAdapter.setPosts(postModel.getList());
                        },
                        err -> error(err));
    }

    public void start() {
        update();
    }

    public void destroy(){
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void setClock(long timestamp){
        long clock = System.currentTimeMillis() - timestamp;
        TextView clockView = view.getClock();
        clockView.setText(String.valueOf(clock));
    }

    @Override
    public void error(Throwable ex){
        super.error(ex);
        if(ex != null) {
            TextView errorView = view.getStatus();
            errorView.setText(ex.getMessage());
        }
    }

    public interface View extends BasePresenterInterface {
        RecyclerView getRecyclerView();
        TextView getStatus();
        TextView getClock();
    }
}
