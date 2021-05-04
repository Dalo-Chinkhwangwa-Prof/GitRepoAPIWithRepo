package com.coolcats.coolcatsapicall.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coolcats.coolcatsapicall.R;
import com.coolcats.coolcatsapicall.databinding.ActivityMainBinding;
import com.coolcats.coolcatsapicall.model.GitResponse;
import com.coolcats.coolcatsapicall.presenter.Contract;
import com.coolcats.coolcatsapicall.presenter.GitPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View, GitAdapter.GitDelegate {

    private Contract.Presenter presenter;
    private GitAdapter gitAdapter = new GitAdapter(new ArrayList<>(), this);

    private ActivityMainBinding binding;
    private ConnectivityManager connectivityManager;


    private BroadcastReceiver connR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()){
                Log.d("TAG_X", "internet connected..!");
            } else
                Log.d("TAG_X", "oops: disconnected.");
        }
    };

    IntentFilter iF = new IntentFilter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iF.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        iF.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        Log.d("TAG_X", "onCreate Activity");

        presenter = new GitPresenter(this);
        presenter.getGitRepositories("Dalo-Chinkhwangwa-Prof");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(gitAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(connR, iF);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(connR);
    }

    @Override
    public void showRepositories(List<GitResponse> repositories) {

        runOnUiThread(() -> {
            Glide.with(binding.getRoot())
                    .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                    .load(
                            "https://avatars.githubusercontent.com/u/47256641?v=4"
                    ).into(binding.avatarImageview);

            gitAdapter.setResponseList(repositories);

        });
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void openRepo(GitResponse repo) {

    }
}