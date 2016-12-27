package com.test.my.complexapp.entities.tables;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.queries.Query;

public class UsersTable {

    @NonNull
    public static final String TABLE = "users";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_NAME = "name";

    @NonNull
    public static final String COLUMN_USERNAME = "username";

    @NonNull
    public static final String COLUMN_EMAIL = "email";

    @NonNull
    public static final String COLUMN_PHONE = "phone";

    @NonNull
    public static final String COLUMN_WEBSITE = "website";

    public static final String COLUMN_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ID;
    public static final String COLUMN_NAME_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_NAME;
    public static final String COLUMN_USERNAME_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_USERNAME;
    public static final String COLUMN_EMAIL_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_EMAIL;
    public static final String COLUMN_PHONE_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_PHONE;
    public static final String COLUMN_WEBSITE_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_WEBSITE;

    // Yep, with StorIO you can safely store queries as objects and reuse them, they are immutable
    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    // This is just class with Meta Data, we don't need instances
    private UsersTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_USERNAME + " TEXT NOT NULL, "
                + COLUMN_EMAIL + " TEXT NOT NULL, "
                + COLUMN_PHONE + " TEXT NOT NULL, "
                + COLUMN_WEBSITE + " TEXT NOT NULL"
                + ");";
    }
}
