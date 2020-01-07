package com.example.moviescue;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviescue.model.Movie;
import com.squareup.picasso.Picasso;


public class MovieDetail extends AppCompatActivity {

   private Movie detailMovie;
   private TextView title;
    private TextView year;
    private TextView vote;
    private TextView overview;
    private ImageView poster;

    @Override
    public void onCreate(  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);

        title = findViewById(R.id.movie_title);
        year = findViewById(R.id.movie_year);
        vote = findViewById(R.id.movie_avg_vote);
        overview = findViewById(R.id.movie_overview);
        poster = findViewById(R.id.movie_poster);





        Intent detailIntent = getIntent();
        if (detailIntent != null) {
            if (detailIntent.hasExtra("movie")) {
                detailMovie = (Movie) detailIntent.getSerializableExtra("movie");


                if (detailMovie.getTitle().length() > 14 ){
                    title.setTextSize(30);
                    title.setText(detailMovie.getTitle());
                }else{
                    title.setTextSize(55);
                    title.setText(detailMovie.getTitle());
                }
                year.setText(detailMovie.getReleaseDate().substring(0, 4));
                title.setText(detailMovie.getTitle());
                overview.setText(detailMovie.getOverview());
                vote.setText(detailMovie.getVoteAvg());

                Picasso.get()
                        .load("https://image.tmdb.org/t/p/w185/" + detailMovie.getImageLink())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(poster);
            }
        }


    }
}
