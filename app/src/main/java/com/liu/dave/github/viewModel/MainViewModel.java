package com.liu.dave.github.viewModel;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.dave.github.GitHubApplication;
import com.liu.dave.github.model.RepoBean;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dave on 16/4/8.
 */
public class MainViewModel implements ViewModel {
    private DataListener dataListener;
    private Context context;

    public MainViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
    }

    public boolean onSearchAction(TextView view, int actionId, KeyEvent event) {
        boolean isSearchAction = actionId == EditorInfo.IME_ACTION_SEARCH;
        if (isSearchAction) {
            String username = view.getText().toString();
            if (username.length() > 0) loadRepos(username);
        }
        return isSearchAction;

    }

    private void loadRepos(String username) {
        GitHubApplication.get(context)
                .getGitHubService()
                .listRepos(username)
                .enqueue(new Callback<List<RepoBean>>() {
                    @Override
                    public void onResponse(Call<List<RepoBean>> call, Response<List<RepoBean>> response) {
                        List<RepoBean> repos = response.body();
                        Toast.makeText(context, "repos size" + (repos != null ? repos.size() : -1), Toast.LENGTH_SHORT).show();
                        if (dataListener != null) dataListener.onReposChanged(repos);
                    }

                    @Override
                    public void onFailure(Call<List<RepoBean>> call, Throwable t) {
                        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    public void destory() {
        context = null;
    }

    public interface DataListener {
        void onReposChanged(List<RepoBean> repos);
    }
}
