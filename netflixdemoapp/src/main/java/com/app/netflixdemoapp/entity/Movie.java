package com.app.netflixdemoapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private int movieId;
    @NotBlank(message = "Movie Name is mandatory")
    @Column(name="movie_name")
    private String movieName;
    @NotBlank(message = "genre is mandatory")
    @Column(name="genre")
    private String genre;
    @NotBlank(message = "Language is mandatory")
    @Column(name="language")
    private String language;

    public Movie(){

    }


    public Movie(int movieId, String movieName, String genre, String language) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.language = language;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
