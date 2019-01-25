package com.igi.kinoteka_api.service.impl;

import com.igi.kinoteka_api.model.Movie;
import com.igi.kinoteka_api.repository.MovieRepository;
import com.igi.kinoteka_api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public void addMovie(Movie movie) {
        if(!movieRepository.findByName(movie.getName()).isPresent()) movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }
}
