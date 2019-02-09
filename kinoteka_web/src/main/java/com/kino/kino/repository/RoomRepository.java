package com.kino.kino.repository;

import com.kino.kino.model.Room;
import com.kino.kino.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
