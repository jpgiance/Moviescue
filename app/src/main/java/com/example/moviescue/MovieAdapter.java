package com.example.moviescue;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;






public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    int img[];
    Context ctx;


    public MovieAdapter( Context context, int[] images ){

        img = images;
        ctx = context;

    }

    @NonNull
    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater movieInflater = LayoutInflater.from(ctx);
        View movieView = movieInflater.inflate(R.layout.movie_item, parent, false);
        movieView.getLayoutParams().height = parent.getHeight()/2;
        MovieHolder holder = new MovieHolder(movieView);

        return holder;
    }

    @Override
    public void onBindViewHolder( @NonNull MovieAdapter.MovieHolder holder, int position ) {


            holder.movieImage1.setImageResource(img[0]);

            holder.movieImage2.setImageResource(img[1]);




    }

    @Override
    public int getItemCount() {
        return 50;
    }




    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView movieImage1;
        ImageView movieImage2;

        public MovieHolder( View itemView){

            super(itemView);


            movieImage1 = (ImageView)itemView.findViewById(R.id.image_1);
            movieImage2 = (ImageView)itemView.findViewById(R.id.image_2);
        }


    }


}

