package com.liu.dave.github.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

import com.liu.dave.github.model.RepoBean;

/**
 * Created by dave on 16/4/10.
 */
public class ItemRepoViewModel extends BaseObservable implements ViewModel {
    private Context context;
    private RepoBean repo;

    public ItemRepoViewModel(Context context, RepoBean repo) {
        this.context = context;
        this.repo = repo;
    }

    public void onItemClick(View view) {
        Toast.makeText(context, repo.toString(), Toast.LENGTH_SHORT).show();
    }

    public void setRepo(RepoBean repo) {
        this.repo = repo;
        notifyChange();
    }

    public String getRepoString() {
        return repo.toString();
    }

    @Override
    public void destory() {

    }
}
