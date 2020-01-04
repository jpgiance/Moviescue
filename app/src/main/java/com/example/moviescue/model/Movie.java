package com.example.moviescue.model;

public class Movie {

    private String title;
    private String releaseDate;
    private String overview;
    private String imageLink;

    private float voteAvg;
    private float popularity;

    public Movie(String movieTitle){
        this.title = movieTitle;
    }





    public void SetReleaseDate(String date){

        this.releaseDate = date;
    }

    public void SetOverview(String summary){

        this.overview = summary;
    }

    public void SetImageLink(String link){

        this.imageLink = link;
    }

    public void SetVoteAvg(Float vote){

        this.voteAvg = vote;
    }

    public void SetPopularity(Float popular){

        this.popularity = popular;
    }

    public String getImageLink(){
        return this.imageLink;
    }

}


