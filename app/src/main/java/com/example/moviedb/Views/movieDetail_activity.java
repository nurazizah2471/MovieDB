package com.example.moviedb.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.ViewModels.MovieViewModel;
import com.example.moviedb.adapters.rvAdapter_genres_movieDetail;
import com.example.moviedb.helpers.Const;
import com.example.moviedb.models.Movies;

public class movieDetail_activity extends AppCompatActivity {

    private TextView title_movie_detail, release_detail_movie, popularity_detail_movie, voteAverage_movie_detail,
            voteCount_movie_detail, overviewText_movieDetails, originalTitle_movieDetail, originalLanguage_movieDetail;
    private ImageView posterPath_movie_detail, bc_Path;
    private String movie_ID;
    private MovieViewModel movieViewModel;
    private RecyclerView rv_genre_movieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        inisialisasi();

        Intent i = getIntent();
        movie_ID= i.getStringExtra("movie_id");

        movieViewModel.getMovieById(movie_ID);
        movieViewModel.getResultGetMovieById().observe(movieDetail_activity.this, showResultMovieinDetail);
    }

    private Observer<Movies> showResultMovieinDetail = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {

            title_movie_detail.setText(movies.getTitle());
            release_detail_movie.setText(movies.getRelease_date());
            popularity_detail_movie.setText(String.valueOf(movies.getPopularity()));
            voteAverage_movie_detail.setText(String.valueOf(movies.getVote_average()));
            voteCount_movie_detail.setText(String.valueOf(movies.getVote_count())+" Peoples");
            overviewText_movieDetails.setText(movies.getOverview());
            originalTitle_movieDetail.setText("Original Title: "+movies.getOriginal_title());
            originalLanguage_movieDetail.setText(movies.getOriginal_language());

            Glide.with(movieDetail_activity.this)
                    .load(Const.IMAGE_PATH +movies.getPoster_path())
                    .into(posterPath_movie_detail);

            Glide.with(movieDetail_activity.this)
                    .load(Const.IMAGE_PATH +movies.getBackdrop_path())
                    .into(bc_Path);

            LinearLayoutManager horizontalLayoutManager
                    = new LinearLayoutManager(movieDetail_activity.this,
                    LinearLayoutManager.HORIZONTAL, false);

            rv_genre_movieDetail.setLayoutManager(horizontalLayoutManager);
            rvAdapter_genres_movieDetail adapter = new rvAdapter_genres_movieDetail(movieDetail_activity.this);
            adapter.setListGenresAdapter(movies.getGenres());
            rv_genre_movieDetail.setAdapter(adapter);
        }
    };

    private void inisialisasi() {

        title_movie_detail=findViewById(R.id.title_movie_detail);
        release_detail_movie=findViewById(R.id.release_detail_movie);
        popularity_detail_movie=findViewById(R.id.popularity_detail_movie);
        voteAverage_movie_detail=findViewById(R.id.voteAverage_movie_detail);
        voteCount_movie_detail=findViewById(R.id.voteCount_movie_detail);
        posterPath_movie_detail=findViewById(R.id.posterPath_movie_detail);
        overviewText_movieDetails=findViewById(R.id.overviewText_movieDetails);
        originalLanguage_movieDetail=findViewById(R.id.originalLanguage_movieDetail);
        bc_Path=findViewById(R.id.bc_Path);
        originalTitle_movieDetail=findViewById(R.id.originalTitle_movieDetail);
        rv_genre_movieDetail=findViewById(R.id.rv_genre_movieDetail);

        movieViewModel=new ViewModelProvider(movieDetail_activity.this).get(MovieViewModel.class);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}