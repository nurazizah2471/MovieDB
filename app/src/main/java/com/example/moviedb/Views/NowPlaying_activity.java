package com.example.moviedb.Views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.R;
import com.example.moviedb.ViewModels.MovieViewModel;
import com.example.moviedb.adapters.rvAdapter_nowPlaying_movieDetail;
import com.example.moviedb.models.NowPlaying;

public class NowPlaying_activity extends AppCompatActivity {

    private RecyclerView rv_nowplaying;
    private MovieViewModel movieViewModel_nowplaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);

        inisialisasi();

        movieViewModel_nowplaying.getNowPlaying();
        movieViewModel_nowplaying.getResultGetNowPlaying().observe(NowPlaying_activity.this, showresultNowPlaying);
    }

    private Observer<NowPlaying> showresultNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_nowplaying.setLayoutManager(new LinearLayoutManager(NowPlaying_activity.this));
            rvAdapter_nowPlaying_movieDetail adapter = new rvAdapter_nowPlaying_movieDetail(NowPlaying_activity.this);
            adapter.setListNowPlayingAdapter(nowPlaying.getResults());
            rv_nowplaying.setAdapter(adapter);
        }
    };

    private void inisialisasi(){
        rv_nowplaying=findViewById(R.id.rv_nowplaying);
        movieViewModel_nowplaying=new ViewModelProvider(NowPlaying_activity.this).get(MovieViewModel.class);
    }
}