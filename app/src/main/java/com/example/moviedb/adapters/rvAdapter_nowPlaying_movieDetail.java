package com.example.moviedb.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.Views.movieDetail_activity;
import com.example.moviedb.helpers.Const;
import com.example.moviedb.models.NowPlaying;

import java.util.List;

public class rvAdapter_nowPlaying_movieDetail extends RecyclerView.Adapter<rvAdapter_nowPlaying_movieDetail.NowPlayingHolder> {

    private Context context;
    private List<NowPlaying.Results> listNowPlaying;

    private List<NowPlaying.Results> getListNowPlaying(){
        return listNowPlaying;
    }

    public void setListNowPlayingAdapter(List<NowPlaying.Results> listNowPlaying){
        this.listNowPlaying=listNowPlaying;
    }

    public rvAdapter_nowPlaying_movieDetail(Context context){
        this.context=context;
    }

    @Override
    public rvAdapter_nowPlaying_movieDetail.NowPlayingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_nowplaying, parent, false);
        return new NowPlayingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapter_nowPlaying_movieDetail.NowPlayingHolder holder, int position) {
        final NowPlaying.Results resultNowPlaying = getListNowPlaying().get(position);
        holder.title_nowplaying.setText(resultNowPlaying.getTitle());
        holder.overview_listNowPlaying.setText(resultNowPlaying.getOverview());
        holder.rating_nowplaying.setText(String.valueOf(resultNowPlaying.getVote_average()));

        Glide.with(context)
                .load(Const.IMAGE_PATH +resultNowPlaying.getPoster_path())
                .into(holder.imagePoster_nowplaying);

        holder.card_nowplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, movieDetail_activity.class);
                intent.putExtra("movie_id", String.valueOf(resultNowPlaying.getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public class NowPlayingHolder extends RecyclerView.ViewHolder {

        TextView title_nowplaying, rating_nowplaying, overview_listNowPlaying;
        ImageView imagePoster_nowplaying;
        CardView card_nowplaying;

        public NowPlayingHolder(@NonNull View itemView) {
            super(itemView);

            title_nowplaying = itemView.findViewById(R.id.title_nowplaying);
            rating_nowplaying = itemView.findViewById(R.id.rating_nowplaying);
            imagePoster_nowplaying = itemView.findViewById(R.id.imagePoster_nowplaying);
            card_nowplaying = itemView.findViewById(R.id.card_nowplaying);
            overview_listNowPlaying=itemView.findViewById(R.id.overview_listNowPlaying);
        }
    }
}
