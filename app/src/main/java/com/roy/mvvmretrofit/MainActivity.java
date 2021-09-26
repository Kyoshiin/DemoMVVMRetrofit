package com.roy.mvvmretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.roy.mvvmretrofit.adapter.MovieListAdapter;
import com.roy.mvvmretrofit.model.MovieModel;
import com.roy.mvvmretrofit.viewmodel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MovieModel> movieList = new ArrayList<>();
    private MovieListAdapter adapter;
    private MovieListViewModel movieViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        TextView tv_noResult = findViewById(R.id.tv_noResults);
//        tv_noResult.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new MovieListAdapter(this,movieList);
        recyclerView.setAdapter(adapter);

        movieViewModel =new ViewModelProvider(this).get(MovieListViewModel.class);
        movieViewModel.getMovieListObserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels!=null){
                    movieList = movieModels;
                    adapter.setMoviesList(movieList);
                }else {
                    tv_noResult.setVisibility(View.VISIBLE);
                }
            }
        });

        movieViewModel.makeApiCall();
    }
}