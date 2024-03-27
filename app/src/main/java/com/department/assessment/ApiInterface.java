package com.department.assessment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/")
    Call<Movies> getMovies(@Query("t") String movieTitle, @Query("apikey") String apiKey);
}
