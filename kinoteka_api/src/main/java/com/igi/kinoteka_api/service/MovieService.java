package com.igi.kinoteka_api.service;

import com.igi.kinoteka_api.model.Movie;

import java.util.List;

public interface MovieService {

    void addMovie(Movie movie);

    List<Movie> getAll();
}
