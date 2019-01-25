package com.igi.kinoteka_api.controller;

import com.igi.kinoteka_api.model.Movie;
import com.igi.kinoteka_api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addMovie(@RequestBody final Movie movie){
        movieService.addMovie(movie);
    }

    @GetMapping
    public List<Movie> getMovies(){
        return movieService.getAll();
    }


}
