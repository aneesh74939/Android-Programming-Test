package com.department.assessment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private Movies moviesList;

    public MoviesAdapter(Movies movies) {
        this.moviesList = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_row_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movies movie = moviesList;  // Assuming you have a getter method in Movies class to get a single movie
        holder.movieName.setText(movie.getTitle());
        holder.movieYear.setText(movie.getYear());
        holder.movieRating.setText(movie.getRated());
        holder.movieDirector.setText(movie.getDirector());
        Glide.with(holder.itemView.getContext())
                .load(movie.getPoster())
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return moviesList != null ? 1 : 0; // Return the number of movies in the list
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView movieName, movieYear, movieDirector, movieRating;
        public ImageView movieImage;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            movieYear = itemView.findViewById(R.id.year_textView);
            movieDirector = itemView.findViewById(R.id.director_textView);
            movieRating = itemView.findViewById(R.id.rating);
            movieImage = itemView.findViewById(R.id.movie_image);
        }
    }
}
