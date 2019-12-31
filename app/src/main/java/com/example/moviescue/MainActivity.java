package com.example.moviescue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView movieRecycler;
    MovieAdapter adapter;

    int images[] = {R.drawable.andrew,
                    R.drawable.gun,
                    };




    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecycler = (RecyclerView)findViewById(R.id.movie_recycler);
        adapter = new MovieAdapter(this, images);

        movieRecycler.setAdapter(adapter);
        movieRecycler.setLayoutManager(new LinearLayoutManager(this));

    }
}
