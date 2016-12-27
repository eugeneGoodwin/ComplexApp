package com.test.my.complexapp;

import com.test.my.complexapp.components.ApplicationComponent;
import com.test.my.complexapp.components.DaggerApplicationComponent;
import com.test.my.complexapp.modules.AppModule;
import com.test.my.complexapp.modules.DBModule;
import com.test.my.complexapp.modules.NetModule;

public class Application extends android.app.Application {
    static Application mApplication;

    public static final String SERVER_ENDPOINT = "https://jsonplaceholder.typicode.com/";

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        mAppComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(SERVER_ENDPOINT))
                .dBModule(new DBModule())
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    public static Application getApplication(){ return mApplication; }
}
