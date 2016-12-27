package com.test.my.complexapp.entities.tables;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.queries.Query;

public class AlbumsTable {

    @NonNull
    public static final String TABLE = "albums";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_USERID = "userId";

    @NonNull
    public static final String COLUMN_ALBUMID = "id";

    @NonNull
    public static final String COLUMN_TITLE = "title";

    public static final String COLUMN_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ID;
    public static final String COLUMN_USERID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_USERID;
    public static final String COLUMN_ALBUMID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ALBUMID;
    public static final String COLUMN_TITLE_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_TITLE;

    // Yep, with StorIO you can safely store queries as objects and reuse them, they are immutable
    @NonNull
    public static final Query QUERY_ALL = Query.builder()
            .table(TABLE)
            .build();

    // This is just class with Meta Data, we don't need instances
    private AlbumsTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_USERID + " INTEGER NOT NULL, "
                + COLUMN_ALBUMID + " INTEGER NOT NULL, "
                + COLUMN_TITLE + " TEXT NOT NULL"
                + ");";
    }
}
