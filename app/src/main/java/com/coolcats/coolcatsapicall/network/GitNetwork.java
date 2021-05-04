package com.coolcats.coolcatsapicall.network;

import android.util.Log;

import com.coolcats.coolcatsapicall.model.GitResponse;
import com.coolcats.coolcatsapicall.model.db.GitCache;
import com.coolcats.coolcatsapicall.model.db.GitDatabase;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GitNetwork {

    public static List<GitResponse> getRepos(String userName){

        try {
                    URL url = new URL("https://api.github.com/users/"
                            + userName +
                            "/repos"
                    );
                    HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String value;
                    StringBuilder bldr = new StringBuilder();

                    while((value = bufferedReader.readLine())!= null){
                        bldr.append(value);
                    }

                    GitDatabase.getDatabase().getDAO().cacheData(new GitCache(1, bldr.toString()));
                    connection.disconnect();

            return getGitResponses(bldr.toString());

        } catch (Exception e) {
            Log.d("TAG_X", e.toString());
            e.printStackTrace();
            Log.d("TAG_X", "Reading from cache or database");
            GitCache cachedJson = GitDatabase.getDatabase().getDAO().getData();
            if(cachedJson == null) {
                Log.d("TAG_X", "No cached result...");
                return new ArrayList<>();
            } else
                return getGitResponses(cachedJson.getValue());
        }
    }

    private static List<GitResponse> getGitResponses(String value) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(value, GitResponse[].class));
    }
}
