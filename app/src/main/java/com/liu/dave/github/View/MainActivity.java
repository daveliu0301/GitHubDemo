package com.liu.dave.github.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.liu.dave.github.R;
import com.liu.dave.github.RepoAdapter;
import com.liu.dave.github.databinding.ActivityMainBinding;
import com.liu.dave.github.model.RepoBean;
import com.liu.dave.github.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {
    private RepoAdapter repoAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this, this);
        repoAdapter = new RepoAdapter();
        binding.setViewModel(mainViewModel);
        binding.recyclerRepo.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerRepo.setAdapter(repoAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.destory();
    }

    @Override
    public void onReposChanged(List<RepoBean> repos) {
        repoAdapter.setRepos(repos);
        repoAdapter.notifyDataSetChanged();

    }
}
