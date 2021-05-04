package com.coolcats.coolcatsapicall.presenter;

import com.coolcats.coolcatsapicall.model.GitResponse;
import com.coolcats.coolcatsapicall.network.GitNetwork;

import java.util.List;

public class GitPresenter implements Contract.Presenter {

    private Contract.View view;

    public GitPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void getGitRepositories(String userName) {

        new Thread(){
            @Override
            public void run() {
                super.run();
                List<GitResponse> resposne = GitNetwork.getRepos(userName);
                if(resposne.isEmpty())
                    view.showError("No Results. :(");
                else
                    view.showRepositories(resposne);
            }
        }.start();

    }
}
