package com.test.my.complexapp.components;


import android.support.annotation.NonNull;


import com.test.my.complexapp.modules.AppModule;
import com.test.my.complexapp.modules.DBModule;
import com.test.my.complexapp.modules.NetModule;
import com.test.my.complexapp.presenters.CacheDBPresenter;
import com.test.my.complexapp.presenters.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetModule.class,
                DBModule.class
        }
)
public interface ApplicationComponent {
    void inject(@NonNull MainPresenter presenter);
    void inject(@NonNull CacheDBPresenter presenter);
}
