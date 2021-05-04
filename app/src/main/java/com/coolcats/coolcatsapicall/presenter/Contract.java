package com.coolcats.coolcatsapicall.presenter;

import com.coolcats.coolcatsapicall.model.GitResponse;

import java.util.List;

public interface Contract {

    interface Presenter {
        void getGitRepositories(String userName);
    }

    interface View {
        void showRepositories(List<GitResponse> repositories);
        void showError(String message);
    }

}
