package com.example.moviescue.utils;

import com.example.moviescue.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final int MOVIES_PER_API_QUERY = 20;






/**
 * This method parses the json obtained from TMDB api and return a list of movies
 *
 * @param json          JSON type String from TMDB api requested
 *
 * @return List of Movie objects from JSON String
*/


    public static List<Movie> parseMovieListJson (String json){

        List<Movie> movieList = new ArrayList<>();      // creating List of Movie objects to be returned

        try {
            JSONObject movieQuery = new JSONObject(json);       // creating json object from string parameter
            JSONArray queryResults = movieQuery.getJSONArray("results");        // creating JSONArray where movies are contained
            JSONObject newMovie;

            // iterating over the JSONArray and initializing each movie object to be appended to movieList
            for (int i = 0; i < MOVIES_PER_API_QUERY; i++) {

                newMovie = queryResults.getJSONObject(i);

                Movie movie = new Movie(newMovie.getString("title"));
                movie.SetReleaseDate(newMovie.getString("release_date"));
                movie.SetOverview(newMovie.getString("overview"));
                movie.SetImageLink(newMovie.getString("poster_path"));
               // movie.SetVoteAvg((Float) newMovie.get("vote_average"));
                //movie.SetPopularity((Float) newMovie.get("popularity"));

                movieList.add(movie);

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return movieList;
    }

}
