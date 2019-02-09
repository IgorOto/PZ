package com.kino.kino.service.impl;

import com.kino.kino.model.*;
import com.kino.kino.repository.MovieRepository;
import com.kino.kino.repository.ShowingRepository;
import com.kino.kino.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;
    private ShowingRepository showingRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ShowingRepository showingRepository) {
        this.movieRepository = movieRepository;
        this.showingRepository = showingRepository;
    }

    @Override
    @Transactional
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional
    public List<Movie> add(Movie movie) {
        List<Movie> dbMovies = movieRepository.findAllByTitle(movie.getTitle());
        if(dbMovies.size() == 0) movieRepository.save(movie);
        return movieRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(long id){
        Movie movie = movieRepository.getOne(id);
        if(movie == null) return;
        List<Showing> showings = showingRepository.findAllByMovie(movie);
        if(showings.size() == 0) movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> getAllByLang(String lang) {
        return movieRepository.findAll();
    }
}
