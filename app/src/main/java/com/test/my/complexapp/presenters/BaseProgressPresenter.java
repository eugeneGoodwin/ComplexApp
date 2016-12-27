package com.test.my.complexapp.presenters;

import android.app.ProgressDialog;
import android.content.Context;

import com.test.my.complexapp.R;

import java.io.IOException;

public class BaseProgressPresenter<T extends BasePresenterInterface> extends BasePresenter<T>{
    static ProgressDialog progress;

    public void showProgress() {
        Context context = view.getContext();
        if (progress == null) {
            progress = ProgressDialog.show(context, "", context.getString(R.string.loading));
        } else {
            progress.show();
        }
    }

    public void hideProgress() {
        if (progress != null) {
            progress.dismiss();
            progress = null;
        }
    }

    @Override
    public void error(Throwable ex){
        if (ex != null) {
            // exception
        }
    }
}
