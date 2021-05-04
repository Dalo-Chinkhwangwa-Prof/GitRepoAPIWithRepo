package com.coolcats.coolcatsapicall;

import android.app.Application;
import android.util.Log;

import com.coolcats.coolcatsapicall.model.db.GitDatabase;

public class GitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GitDatabase.buildInstance(this);
        Log.d("TAG_X", "onCreate -> Application database built!");
    }
}
