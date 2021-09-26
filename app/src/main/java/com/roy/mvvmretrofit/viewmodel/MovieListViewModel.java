package com.roy.mvvmretrofit.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.roy.mvvmretrofit.model.MovieModel;
import com.roy.mvvmretrofit.network.APIService;
import com.roy.mvvmretrofit.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movieList;

    public MovieListViewModel() {
        this.movieList = new MutableLiveData<>();
    }

    //to return the oberver
    public MutableLiveData<List<MovieModel>> getMovieListObserver(){
        return movieList;
    }

    //for api call via Retrofit
    public void makeApiCall(){
        APIService apiService = RetroInstance.getRetrofit().create(APIService.class);
        Call<List<MovieModel>> call = apiService.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                movieList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                movieList.postValue(null);
            }
        });
    }
}
