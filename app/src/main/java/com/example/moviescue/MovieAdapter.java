package com.example.moviescue;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviescue.model.Movie;
import com.example.moviescue.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {


    private Context ctx;
    private List<Movie> moviesList;
    private Picasso mPicasso;
    private final MovieAdapterOnClickHandler mClickHandler;




    public interface MovieAdapterOnClickHandler {
        void onClick();// void onClick(Movie movie);
    }

    public MovieAdapter( Context context, MovieAdapterOnClickHandler clickHandler ){

      ctx = context;
      mPicasso = new Picasso.Builder(context).build();
      mClickHandler = clickHandler;

    }

    public class MovieHolder extends RecyclerView.ViewHolder implements OnClickListener {

        ImageView movieImage1;
        ImageView movieImage2;



        public MovieHolder( View itemView){

            super(itemView);

            movieImage1 = itemView.findViewById(R.id.image_1);
            movieImage2 = itemView.findViewById(R.id.image_2);

            //itemView.setOnClickListener(this);
            movieImage1.setOnClickListener(this);
            //movieImage2.setOnClickListener(this);
        }

        @Override
        public void onClick( View v ) {
            int adapterPosition = getAdapterPosition();
            //Movie selectedMovie = moviesList.get(adapterPosition);
            //mClickHandler.onClick(selectedMovie);
            mClickHandler.onClick();
        }
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

        LayoutInflater movieInflater = LayoutInflater.from(ctx);
        View movieView = movieInflater.inflate(R.layout.movie_item, parent, false);
        movieView.getLayoutParams().height = parent.getHeight()/2;
        MovieHolder holder = new MovieHolder(movieView);

        return holder;
    }

    @Override
    public void onBindViewHolder( @NonNull MovieAdapter.MovieHolder holder, int position ) {


        mPicasso.get()
                .load(loadMoviePoster(position*2))
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.movieImage1);


        mPicasso.get()
                .load(loadMoviePoster(position*2 + 1))
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.movieImage2);


    }

    @Override
    public int getItemCount(){
        if (null == moviesList) {
            return 0;
        }
        return moviesList.size()/2;

    }

    /**
     *
     *
     * @param movies
     */
    public void setMoviesList(List<Movie> movies) {
        moviesList = movies;
        notifyDataSetChanged();
    }

    public String loadMoviePoster(int pos){

        return ("https://image.tmdb.org/t/p/w185/" + this.moviesList.get(pos).getImageLink());
    }





}

