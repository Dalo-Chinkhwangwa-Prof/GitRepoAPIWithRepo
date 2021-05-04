package com.coolcats.coolcatsapicall.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coolcats.coolcatsapicall.databinding.RepoItemLayoutBinding;
import com.coolcats.coolcatsapicall.model.GitResponse;

import java.util.List;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.GitViewHolder>  {

    private List<GitResponse> responseList;
    private GitDelegate gitDelegate;


    public GitAdapter(List<GitResponse> responseList, GitDelegate gitDelegate) {
        this.responseList = responseList;
        this.gitDelegate = gitDelegate;
    }

    public void setResponseList(List<GitResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    public interface GitDelegate{
        void openRepo(GitResponse repo);
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RepoItemLayoutBinding binding = RepoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new GitViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder holder, int position) {
        GitResponse response = responseList.get(position);
        holder.binding.repoNameTextview.setText(response.getName());
        holder.binding.languageTextview.setText("Created in "+response.getLanguage());
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    class GitViewHolder extends RecyclerView.ViewHolder {
        RepoItemLayoutBinding binding;
        public GitViewHolder(RepoItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
