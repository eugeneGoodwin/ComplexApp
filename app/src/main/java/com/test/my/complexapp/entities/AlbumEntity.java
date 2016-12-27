package com.test.my.complexapp.entities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.test.my.complexapp.entities.tables.AlbumsTable;

@StorIOSQLiteType(table = AlbumsTable.TABLE)
public class AlbumEntity {

    @Nullable
    @StorIOSQLiteColumn(name = AlbumsTable.COLUMN_ID, key = true)
    Long id;

    @NonNull
    @StorIOSQLiteColumn(name = AlbumsTable.COLUMN_USERID)
    Long userId;

    @NonNull
    @StorIOSQLiteColumn(name = AlbumsTable.COLUMN_ALBUMID)
    Long albumId;

    @NonNull
    @StorIOSQLiteColumn(name = AlbumsTable.COLUMN_TITLE)
    String title;

    AlbumEntity() {
    }

    @NonNull
    public static AlbumEntity newAlbum(@Nullable Long id, @NonNull Long userId, @NonNull Long albumId, @NonNull String title) {
        AlbumEntity album = new AlbumEntity();
        album.id = id;
        album.userId = userId;
        album.albumId = albumId;
        album.title = title;
        return album;
    }

    @NonNull
    public static AlbumEntity newAlbum(@NonNull Long userId, @NonNull Long albumId, @NonNull String title) {
        return newAlbum(null, userId, albumId, title);
    }

    @Nullable
    public Long id() {
        return id;
    }

    @NonNull
    public Long userId() {
        return userId;
    }

    @NonNull
    public Long albumId() {
        return albumId;
    }

    @NonNull
    public String title() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumEntity that = (AlbumEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!userId.equals(that.userId)) return false;
        if (!albumId.equals(that.albumId)) return false;
        return title.equals(that.title);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + userId.hashCode();
        result = 31 * result + albumId.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AlbumEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                '}';
    }
}
