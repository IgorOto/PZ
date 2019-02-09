package com.kino.kino.service;

import com.kino.kino.model.Room;
import com.kino.kino.model.Seat;

import java.util.List;

public interface RoomService {
    List<Room> getAll();

	List<Seat> getSeatsFor(long roomId);
}
