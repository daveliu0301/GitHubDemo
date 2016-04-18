package com.liu.dave.github;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.liu.dave.github.databinding.ItemRepoBinding;
import com.liu.dave.github.model.RepoBean;
import com.liu.dave.github.viewModel.ItemRepoViewModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by dave on 16/4/10.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {
    private List<RepoBean> repos = Collections.emptyList();

    public RepoAdapter() {
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRepoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_repo,
                parent,
                false);
        return new RepoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bindRepo(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {
        final ItemRepoBinding binding;

        public RepoViewHolder(ItemRepoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindRepo(RepoBean repo) {
            ItemRepoViewModel viewModel = binding.getViewModel();
            if (viewModel == null) {
                binding.setViewModel(new ItemRepoViewModel(itemView.getContext(), repo));
            } else {
                viewModel.setRepo(repo);
            }
        }
    }

    public void setRepos(List<RepoBean> repos) {
        if (repos != null)
            this.repos = repos;
    }
}
