package com.example.moviedb.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.models.Movies;
import com.example.moviedb.models.NowPlaying;
import com.example.moviedb.repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository= MovieRepository.getInstance();
    }

    //==Begin of viewModel to get Movie ById
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();

    public void getMovieById(String movieId){
        resultGetMovieById = movieRepository.getMovieData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }
    //==End of viewModel to get Movie ById


    //==Begin of viewModel to getnowplaying
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();

    public void getNowPlaying(){
        resultGetNowPlaying = movieRepository.getNowPlaying();
    }
    public LiveData<NowPlaying> getResultGetNowPlaying(){
        return resultGetNowPlaying;
    }
    //==End of viewModel to getnowplaying
}
