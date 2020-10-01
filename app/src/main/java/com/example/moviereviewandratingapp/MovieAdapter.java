package com.example.moviereviewandratingapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviereviewandratingapp.databinding.ItemMovieBinding;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private LayoutInflater layoutInflater;
    private final List<Movie> movies;

    public MovieAdapter(@NonNull List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // this function makes sure the textview or cardview that we are putting in the card are binded properly and shown; onCreateViewHolder: We have to create the holder which holds view
        if (layoutInflater == null) { //
            layoutInflater = LayoutInflater.from(parent.getContext()); // if layout is null display the main screen
        }
        ItemMovieBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false); //setting the card view with the fragment
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) { // for binding data
        holder.binding.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() { // count the number of items so that it can make sure how many cards it have to make
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding binding; //name given by android by camel case + Binding

        MovieViewHolder(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot()); //Bind the top most element
            this.binding = binding;
        }
    }
}
