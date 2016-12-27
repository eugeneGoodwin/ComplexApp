package com.test.my.complexapp.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.test.my.complexapp.entities.tables.UsersTable;

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(@NonNull Context context) {
        super(context, "complex_db", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(UsersTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        // no impl
    }
}
