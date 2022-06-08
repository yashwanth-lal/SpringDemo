package com.app.netflixdemoapp.controller;


import com.app.netflixdemoapp.dto.MovieDto;
import com.app.netflixdemoapp.entity.Movie;
import com.app.netflixdemoapp.exceptionhandlers.MovieNotFoundException;
import com.app.netflixdemoapp.service.MovieDtoService;
import com.app.netflixdemoapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/movieApi")
public class MovieRestController {
    private MovieService movieService;

    @Autowired
    private MovieDtoService dtoService;

    @Autowired
    public MovieRestController(MovieService movieService){
        this.movieService = movieService;
    }


    @GetMapping("/")
    public String home(){
        return "redirect:movies";
    }

    @GetMapping("/getMoviesUsingDto")
    @ResponseBody
    public List<MovieDto> getMovies(){
        return dtoService.findAll();
    }

    @GetMapping("/addMovie")
    public String addFunc( Model model){

        MovieDto b = new MovieDto();
        model.addAttribute("movie",b);
        return "add-a-movie";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateFunc(@PathVariable(value="id") int id, Model model){
        Movie b = movieService.findById(id);
        model.addAttribute("movie",b);
        return "update-a-movie";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable(value="id") int id,Model model){
        Movie b = movieService.findById(id);

        if(b == null){
            throw new MovieNotFoundException("Movie Id not found- "+id);
        }
        movieService.deleteById(id);
        return "redirect:/movieApi/movies";
    }

    @GetMapping("/movies")
    public String findAll(Model model){
        model.addAttribute("movies",movieService.findAll());
        return "listmovies";
    }

    @GetMapping("/movies/{movieId}")
    public Movie getMovie(@PathVariable int movieId){

        Movie b = movieService.findById(movieId);
        if(b == null){
            throw new MovieNotFoundException("Movie Id not found- "+movieId);
        }
        return b;
    }

    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute MovieDto b, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "errorPage";
        }
        movieService.save(b.toMovie());
        return "redirect:movies";
    }

    @GetMapping("/moviesView")
    public String moviesViewForUser(Model model){
        model.addAttribute("movies",movieService.findAll());
        return "list-of-movies-for-user";
    }
    @GetMapping("/moviesPlay")
    public String moviesPlayForUser(@RequestParam("movieId") int movieId, Model model){
        model.addAttribute("movie",movieService.findById(movieId));
        return "play-movies-for-user";
    }
    @GetMapping("/access")
    public String accessDenied()
    {
        return "accessDeny.html";
    }

}
