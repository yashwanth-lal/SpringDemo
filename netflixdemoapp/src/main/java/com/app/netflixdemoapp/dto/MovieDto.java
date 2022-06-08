package com.app.netflixdemoapp.dto;

import com.app.netflixdemoapp.entity.Movie;
import lombok.Data;


@Data
public class MovieDto {

    private int movieId;
    private String movieName;
    private String genre;
    private String language;

    public MovieDto() {
    }
    public MovieDto(Movie movie) {
        this.movieId=movie.getMovieId();
        this.movieName=movie.getMovieName();
        this.genre=movie.getGenre();
        this.language=movie.getLanguage();
    }

    public Movie toMovie(){
        return new Movie(movieId,movieName,genre,language);
    }
}