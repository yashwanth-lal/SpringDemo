package com.app.netflixdemoapp.service;
import com.app.netflixdemoapp.dao.MovieRepository;
import com.app.netflixdemoapp.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int id) {
        Optional<Movie> result = movieRepository.findById(id);
        Movie movie = null;
        if(result.isPresent()) {
            movie = result.get();
        }
        else{
            throw new RuntimeException("Did not find Movie Id- "+id);
        }
        return movie;
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }
}
