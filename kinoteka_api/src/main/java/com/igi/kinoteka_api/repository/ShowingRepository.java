package com.igi.kinoteka_api.repository;

import com.igi.kinoteka_api.model.Movie;
import com.igi.kinoteka_api.model.Showing;
import com.igi.kinoteka_api.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShowingRepository extends JpaRepository<Showing, Long> {

    List<Showing> findAllByMovie(Movie movie);

    List<Showing> findAllByStartBetween(LocalDateTime start, LocalDateTime end);

    Optional<Showing> findByRoomAndStartGreaterThanEqualAndEndLessThanEqual(Room room, LocalDateTime start, LocalDateTime end);
}
