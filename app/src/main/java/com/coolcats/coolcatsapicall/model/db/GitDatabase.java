package com.coolcats.coolcatsapicall.model.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {GitCache.class})
public abstract class GitDatabase extends RoomDatabase {

    public abstract CacheDao getDAO();

    public static GitDatabase getDatabase() {
        return database;
    }

    private static GitDatabase database;

    public static void buildInstance(Context context){
        database = Room.databaseBuilder(
                context,
                GitDatabase.class,
                "git.db"
        ).build();

    }





}
