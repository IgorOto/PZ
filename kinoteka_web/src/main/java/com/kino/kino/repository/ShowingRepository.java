package com.kino.kino.repository;

import com.kino.kino.model.Showing;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kino.kino.model.*;

public interface ShowingRepository extends JpaRepository<Showing, Long> {
    List<Showing> findAllByMovie(Movie movie);
    List<Showing> findAllByRoomAndDatetimeBetweenOrRoomAndEndDatetimeBetween(Room room, LocalDateTime start, LocalDateTime end, Room room2, LocalDateTime start2, LocalDateTime end2);
    List<Showing> findAllByMovieAndDatetimeAfterOrderByDatetime(Movie movie, LocalDateTime datetime);
    List<Showing> findAllByDatetimeBetweenAndMovie_LangIgnoreCaseOrderByMovie(LocalDateTime start, LocalDateTime end, String lang);
    List<Showing> findAllByDatetimeBetweenOrderByMovie(LocalDateTime start, LocalDateTime end);
}
