package com.department.assessment.ui.on_boarding;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.department.assessment.ApiInterface;
import com.department.assessment.Movies;
import com.department.assessment.R;
import com.department.assessment.MoviesAdapter;
import com.department.assessment.utils.HelperClass;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private List<Movies> movieList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerView = findViewById(R.id.movies_recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DashboardActivity.this);
        recyclerView.setLayoutManager(layoutManager);


        fetchMoviesFromApi();
    }

    private void setMoviesList(Movies movies) {
        recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new MoviesAdapter(movies);
        recyclerView.setAdapter(adapter);
        Log.d("Adapter", "Adapter set");
    }

    private void fetchMoviesFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<Movies> call = service.getMovies("love","942e1ef1");
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()) {
                    Movies movieResponse = response.body();
                    assert movieResponse != null;
                    if(Integer.parseInt(movieResponse.getYear()) > 2000) {
                        setMoviesList(movieResponse);
                    }
                    else {
                        HelperClass.showSnackbarwithMsg(DashboardActivity.this,"No movies found");
                    }
                }
                else{
                    assert response.errorBody() != null;
                    Log.e("API error", response.message());
                }
            }


            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                HelperClass.showErrorSnackbar(DashboardActivity.this);
            }
        });
    }
}



