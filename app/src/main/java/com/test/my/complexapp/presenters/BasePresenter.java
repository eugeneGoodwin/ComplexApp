package com.test.my.complexapp.presenters;

public abstract class BasePresenter<T extends BasePresenterInterface> {

    protected T view;

    public final void attachView(T t) {
        this.view = t;
    }

    public final void detachView() {
        view = null;
    }

    public abstract void error(Throwable ex);
}
