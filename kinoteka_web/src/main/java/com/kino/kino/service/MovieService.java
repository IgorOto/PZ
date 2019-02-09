package com.kino.kino.service;

import com.kino.kino.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> add(Movie movie);

    void delete(long id);

	List<Movie> getAllByLang(String lang);
}
