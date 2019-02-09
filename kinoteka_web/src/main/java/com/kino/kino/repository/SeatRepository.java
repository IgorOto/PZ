package com.kino.kino.repository;

import java.util.List;

import com.kino.kino.model.Room;
import com.kino.kino.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByRoom_id(long roomId);
}
