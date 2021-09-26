package com.roy.mvvmretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.roy.mvvmretrofit.R;
import com.roy.mvvmretrofit.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Context context;
    private List<MovieModel> movieModelList = new ArrayList<>();

    public MovieListAdapter(Context context, List<MovieModel> movieModelList) {
        this.context = context;
        this.movieModelList = movieModelList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();//TODO: see if works else cons
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieModel currentMovie = movieModelList.get(position);
        holder.textViewTitle.setText(currentMovie.getTitle().toString());
        Glide.with(context)
                .load(currentMovie.getUrl())
                .apply(RequestOptions.centerCropTransform())
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.imageViewMovie);
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public void setMoviesList(List<MovieModel> movies){
        this.movieModelList = movies;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        ImageView imageViewMovie;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.movieTitleView);
            imageViewMovie = itemView.findViewById(R.id.movieImageView);
        }
    }
}
