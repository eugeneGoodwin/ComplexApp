package com.test.my.complexapp.modules;

import android.content.Context;

import com.test.my.complexapp.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    Context providesContext(){
        return mApplication;
    }

}
