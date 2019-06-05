package com.lcz.mua.base;

import com.lcz.mua.entity.Contributor;
import com.lcz.mua.entity.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * desc TODO
 * Created by lcz on 2019/6/4.
 */
public interface APIService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}
