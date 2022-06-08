package com.app.netflixdemoapp.dao;

import com.app.netflixdemoapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
