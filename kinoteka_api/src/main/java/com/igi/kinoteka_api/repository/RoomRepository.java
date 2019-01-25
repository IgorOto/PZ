package com.igi.kinoteka_api.repository;

import com.igi.kinoteka_api.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByName(String name);
}
