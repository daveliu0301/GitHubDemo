package com.liu.dave.github;

import android.app.Application;
import android.content.Context;

/**
 * Created by LiuDong on 2016/4/8.
 */
public class GitHubApplication extends Application {
    private GitHubService gitHubService;

    public static GitHubApplication get(Context context) {
        return (GitHubApplication) context.getApplicationContext();
    }

    public GitHubService getGitHubService() {
        if (gitHubService == null)
            gitHubService = GitHubService.Factory.create();
        return gitHubService;
    }

    public void setGitHubService(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }
}
