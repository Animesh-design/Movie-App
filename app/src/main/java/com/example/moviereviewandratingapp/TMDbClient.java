package com.example.moviereviewandratingapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class TMDbClient {
    private static final Object LOCK = new Object();
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static TMDbAPI sTMDbAPI;
    private static TMDbClient sInstance;

    // Private Constructor for Singleton pattern
    private TMDbClient() {
    }

    public static TMDbClient getInstance() { //
        if (sInstance == null || sTMDbAPI == null) { // if request is not null the synchronize our request so that it wont get changed
            synchronized (LOCK) {
                // Building OkHttp client
                OkHttpClient httpClient = new OkHttpClient.Builder().build(); //this OKHttp class is used to sent request to server

                // Build Retrofit instance
                Retrofit.Builder builder = new Retrofit.Builder() // retrofit object
                        .baseUrl(BASE_URL) // base url added
                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create()); // asking to return data in gson format

                // Create API from Retrofit instance
                sInstance = new TMDbClient();
                sTMDbAPI = builder.build().create(TMDbAPI.class);
            }
        }
        return sInstance;
    }

    public LiveData<List<Movie>> getPopularMovies(String apiKey) { // data respose coming
        final MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>(); // mutable data coming soo that new data can come every time if new movies come
        /*sending to interface TMDbAPI we created*/sTMDbAPI.getPopularMovies(apiKey).enqueue(new Callback<MovieNetworkResponse>() { //if we send one request and send another it will be saved in queue
            @Override
            public void onResponse(@NonNull Call<MovieNetworkResponse> call,
                                   @NonNull Response<MovieNetworkResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.postValue(response.body().getMovies()); // if data coming is not null then add it to the result list
                } else {
                    Timber.e(response.message()); // or show any error message
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieNetworkResponse> call, @NonNull Throwable t) {
                Timber.i("getPopularMovies() onFailure"); // if request is not shown wecan print or we can also show toast
                Timber.e(t);
            }
        });
        return mutableLiveData;
    }

    public LiveData<List<Movie>> getTopRatedMovies(String apiKey) { // same for top rated movies
        final MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
        sTMDbAPI.getTopRatedMovies(apiKey).enqueue(new Callback<MovieNetworkResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieNetworkResponse> call,
                                   @NonNull Response<MovieNetworkResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.postValue(response.body().getMovies());
                } else {
                    Timber.e(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieNetworkResponse> call, @NonNull Throwable t) {
                Timber.e(t);
            }
        });
        return mutableLiveData; // returning the lst
    }
}