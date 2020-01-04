package com.example.moviescue;

import com.example.moviescue.utils.JsonUtils;
import com.example.moviescue.utils.NetworkUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieRecycler;
    private MovieAdapter adapter;
    private ProgressBar updateIndicator;
    private TextView errorMessage;
    String sortingFilter = "1";                                  // sorting filter state variable (by popularity = 1) (by vote_average = 2) (Default = 1)

   // public static Picasso mPicasso;

    int images[] = {R.drawable.andrew,
            R.drawable.gun,
    };



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorMessage = findViewById(R.id.error_message);
        movieRecycler = findViewById(R.id.movie_recycler);
        adapter = new MovieAdapter(this, images);
        movieRecycler.setAdapter(adapter);
        movieRecycler.setLayoutManager(new LinearLayoutManager(
                                                    this,
                                                            LinearLayoutManager.VERTICAL,
                                                false));

        updateIndicator = findViewById(R.id.update_indicator);


        updateActivity();

    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.query_settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        int id = item.getItemId();

        switch (id){

            case R.id.sort_1:
                sortingFilter = "1";
                updateActivity();
                return true;


            case R.id.sort_2:
                sortingFilter = "2";
                updateActivity();
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

    private void updateActivity(){

        errorMessage.setVisibility(View.INVISIBLE);
        movieRecycler.setVisibility(View.VISIBLE);

        new FetchMovieData().execute(sortingFilter);
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

            int sortPreference = Integer.valueOf(params[0]);
            URL apiQuery = NetworkUtils.buildUrl(sortPreference);

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
