package com.example.moviescue.model;

public class Movie {

    private String title;
    private String releaseDate;
    private String overview;
    private String imageLink;

    private String voteAvg;
    private String popularity;

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

    public void SetVoteAvg(String vote){

        this.voteAvg = vote;
    }

    public void SetPopularity(String popular){

        this.popularity = popular;
    }

    public String getImageLink(){
        return this.imageLink;
    }

    public String getTitle(){
        return this.title;
    }

    public String getReleaseDate(){
        return this.releaseDate;
    }

    public String getOverview(){
        return this.overview;
    }

    public String getVoteAvg(){
        return this.voteAvg;
    }

}


