package com.example.moviescue;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviescue.model.Movie;


public class MovieDetail extends AppCompatActivity {

   private Movie detailMovie;
   private TextView title;
    private TextView year;
    private TextView duration;
    private TextView vote;
    private TextView overview;

    @Override
    public void onCreate(  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);

        title = findViewById(R.id.movie_title);
//        year = findViewById(R.id.movie_year);
//        duration = findViewById(R.id.movie_duration);
//        vote = findViewById(R.id.movie_avg_vote);
//        overview = findViewById(R.id.movie_overview);





//        Intent detailIntent = getIntent();
//        if (detailIntent != null) {
//            if (detailIntent.hasExtra(Intent.EXTRA_TEXT)) {
//                detailMovie = (Movie) detailIntent.getSerializableExtra("movie");
//
//                title.setText(detailMovie.getTitle());
//                year.setText(detailMovie.getReleaseDate());
//                title.setText(detailMovie.getTitle());
//                overview.setText(detailMovie.getOverview());
//            }
//        }


    }
}
