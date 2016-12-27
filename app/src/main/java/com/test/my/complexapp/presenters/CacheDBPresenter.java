package com.test.my.complexapp.presenters;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.test.my.complexapp.Application;
import com.test.my.complexapp.adapters.RecycleViewUserAdapter;
import com.test.my.complexapp.entities.User;
import com.test.my.complexapp.models.UserModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class CacheDBPresenter extends BaseProgressPresenter<CacheDBPresenter.View>{

    @Inject
    UserModel userModel;

    RecycleViewUserAdapter userAdapter;

    private Observable<List<User>> mObservable;

    private Subscription dbSubscription = Subscriptions.empty();
    private Subscription statusSubscription = Subscriptions.empty();

    public void init(){

        Application.getApplication().getAppComponent().inject(this);

        RecyclerView recyclerView = view.getRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        userAdapter = new RecycleViewUserAdapter(null);
        recyclerView.setAdapter(userAdapter);

        mObservable = userModel.getObservable();
        //mObservable.unsubscribeOn(Schedulers.computation()); // because of this https://github.com/square/retrofit/issues/1046

        dbSubscription = mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(l -> {
                            userAdapter.setUsers(l);
                        },
                        err -> error(err));
        update();
    }

    public void update() {
        Observable<String> statusObservable = userModel.updateUsers();
        statusSubscription = statusObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> netStatus(s),
                        e -> error(e));
    }

    @Override
    public void error(Throwable ex){
        super.error(ex);
        if(ex != null)
            status(ex.getMessage());
    }

    public void status(String status){
        TextView errorView = view.getStatus();
        errorView.setText(status);
    }

    public void netStatus(String status){
        TextView errorView = view.getNetStatus();
        errorView.setText(status);
    }

    public void destroy(){
        if (dbSubscription != null && !dbSubscription.isUnsubscribed()) {
            dbSubscription.unsubscribe();
        }
        if (statusSubscription != null && !statusSubscription.isUnsubscribed()) {
            statusSubscription.unsubscribe();
        }
    }

    public interface View extends BasePresenterInterface {
        RecyclerView getRecyclerView();
        TextView getStatus();
        TextView getNetStatus();
    }
}
