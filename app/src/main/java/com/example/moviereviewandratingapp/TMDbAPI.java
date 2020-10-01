package com.example.moviereviewandratingapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbAPI {
    @GET("movie/popular") // request to get popular movies
    Call<MovieNetworkResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieNetworkResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}