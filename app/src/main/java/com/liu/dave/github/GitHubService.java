package com.liu.dave.github;

import com.liu.dave.github.Model.RepoBean;
import com.liu.dave.github.Model.UserBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by LiuDong on 2016/4/8.
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<RepoBean>> listRepos(@Path("user") String user);

    @GET
    Call<UserBean> userFromUrl(@Url String userUrl);

    class Factory {
        public static GitHubService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(GitHubService.class);
        }
    }
}
