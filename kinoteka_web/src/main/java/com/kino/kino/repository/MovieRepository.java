package com.kino.kino.repository;

import java.util.List;

import com.kino.kino.model.Movie;
import com.kino.kino.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findAllByTitle(String title);
	List<Movie> findAllByLang(String lang);
}
