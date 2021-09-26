package com.roy.mvvmretrofit.network;

import com.roy.mvvmretrofit.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("photos")
    Call<List<MovieModel>> getMovieList();
}
