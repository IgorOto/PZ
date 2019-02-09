package com.kino.kino.controller.api;

import com.kino.kino.model.Movie;
import com.kino.kino.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getMovies(){
        return movieService.getAll();
    }

    @GetMapping("{lang}")
    public List<Movie> getMoviesByLang(@PathVariable String lang){
        return movieService.getAllByLang(lang);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Movie> add(@RequestBody Movie movie){
        System.out.println(movie.toString());
        return movieService.add(movie);
    }

    @DeleteMapping("{movieId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long movieId){
        movieService.delete(movieId);
    }
}
