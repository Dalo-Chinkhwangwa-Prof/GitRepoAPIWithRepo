package com.coolcats.coolcatsapicall.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "gitcache")
public class GitCache {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "value")
    private String value;

    public GitCache(int id, String value) {
        this.id = id;
        this.value = value;
    }

    @Ignore
    public GitCache(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
