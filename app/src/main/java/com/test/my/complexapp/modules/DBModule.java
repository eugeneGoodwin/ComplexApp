package com.test.my.complexapp.modules;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.test.my.complexapp.db.DBOpenHelper;
import com.test.my.complexapp.entities.User;
import com.test.my.complexapp.entities.UserSQLiteTypeMapping;
import com.test.my.complexapp.models.UserModel;
import com.test.my.complexapp.services.NetService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {
    @Provides
    @NonNull
    @Singleton
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(User.class, new UserSQLiteTypeMapping())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context) {
        return new DBOpenHelper(context);
    }

    @Provides
    @NonNull
    @Singleton
    public UserModel provideUserModel(@NonNull NetService netService, @NonNull StorIOSQLite storIOSQLite) {
        return new UserModel(netService, storIOSQLite);
    }
}
