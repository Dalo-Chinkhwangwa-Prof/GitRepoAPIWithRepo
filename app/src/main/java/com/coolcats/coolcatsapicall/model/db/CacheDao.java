package com.coolcats.coolcatsapicall.model.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CacheDao {

    @Query("SELECT * FROM gitcache")
    GitCache getData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void cacheData(GitCache cache);
}
