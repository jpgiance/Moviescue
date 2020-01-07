package com.example.moviescue;

import com.example.moviescue.model.Movie;
import com.example.moviescue.utils.JsonUtils;
import com.example.moviescue.utils.NetworkUtils;
import com.example.moviescue.MovieAdapter.MovieAdapterOnClickHandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapterOnClickHandler {

    private RecyclerView movieRecycler;
    private MovieAdapter adapter;
    private ProgressBar updateIndicator;
    private TextView errorMessage;
    private MenuItem popularityFilter;
    private MenuItem reviewFilter;

    private String POPULARITY = "popularity";
    private String REVIEW = "review";
    private String filter = POPULARITY;



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        popularityFilter = findViewById(R.id.sort_1);
        reviewFilter = findViewById(R.id.sort_2);
        errorMessage = findViewById(R.id.error_message);
        movieRecycler = findViewById(R.id.movie_recycler);
        adapter = new MovieAdapter(this, this);
        movieRecycler.setAdapter(adapter);
        movieRecycler.setLayoutManager(new LinearLayoutManager(
                                                    this,
                                                            LinearLayoutManager.VERTICAL,
                                                false));
        movieRecycler.setHasFixedSize(true);
        updateIndicator = findViewById(R.id.update_indicator);



        updateActivity(filter);

    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class movieDetail = MovieDetail.class;
        Intent newIntent = new Intent(context, movieDetail);
        newIntent.putExtra("movie", (Serializable) movie);
        startActivity(newIntent);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.query_settings, menu);

        popularityFilter = menu.findItem(R.id.sort_1);
        reviewFilter = menu.findItem(R.id.sort_2);
        popularityFilter.setChecked(true);
        reviewFilter.setChecked(false);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        int id = item.getItemId();



        switch (id){

            case R.id.sort_1:
                popularityFilter.setChecked(true);
                reviewFilter.setChecked(false);
                filter = POPULARITY;
                updateActivity(filter);
                return true;


            case R.id.sort_2:
                popularityFilter.setChecked(false);
                reviewFilter.setChecked(true);
                filter = REVIEW;
                updateActivity(filter);
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }


    }

    private void showMoviesView() {

        errorMessage.setVisibility(View.INVISIBLE);
        movieRecycler.setVisibility(View.VISIBLE);
    }


    private void showErrorMessage() {

        movieRecycler.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }


    /**
     * This method will get the user's sorting preference and
     * invoke other method to query the TMDB api
     */

    private void updateActivity(String filter){

        errorMessage.setVisibility(View.INVISIBLE);
        movieRecycler.setVisibility(View.VISIBLE);



        new FetchMovieData().execute(filter);

    }



    public class FetchMovieData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            updateIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground( String... params ) {

            if (params.length == 0) {
                return null;
            }

            URL apiQuery = NetworkUtils.buildUrl(params[0]);

            try {
                return NetworkUtils.getResponseFromHttpUrl(apiQuery);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }


        @Override
        protected void onPostExecute( String queryResponse ) {
            updateIndicator.setVisibility(View.INVISIBLE);
            if (queryResponse != null) {
                showMoviesView();
                adapter.setMoviesList(JsonUtils.parseMovieListJson(queryResponse));
            } else {
                showErrorMessage();
            }
        }
    }

}
