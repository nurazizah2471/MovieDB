package com.example.moviedb.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.helpers.Const;
import com.example.moviedb.models.Movies;
import com.example.moviedb.models.NowPlaying;
import com.example.moviedb.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository movieRepository;

    private MovieRepository() {
    }

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MutableLiveData<Movies> getMovieData(String movie_id) {

        final MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endpoint().getMoviesById(Integer.parseInt(movie_id), Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlaying() {

        final MutableLiveData<NowPlaying> result = new MutableLiveData<>();

        ApiService.endpoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<NowPlaying>() {

            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });
        return result;
    }
}
