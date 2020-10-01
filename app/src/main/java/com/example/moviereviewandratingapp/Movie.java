package com.example.moviereviewandratingapp;

import com.google.gson.annotations.SerializedName;

import lombok.Data;


@Data
public class Movie {

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p"; // base url for loading images
    public static final String BASE_POSTER_LARGE_URL = BASE_IMAGE_URL + "/w342"; // image size will be managed automatically

    @SerializedName("title") String title;  // these 3 lines are getter setter
    @SerializedName("vote_average") float votes;
    @SerializedName("poster_path") String posterPath;

    public String getLargePosterUrl() {
        return BASE_POSTER_LARGE_URL + posterPath;
    }
}