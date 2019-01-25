package com.igi.kinoteka_api.service;

import com.igi.kinoteka_api.model.Room;
import com.igi.kinoteka_api.model.dto.RoomDto;

import java.util.List;

public interface RoomSeatService {

    Room getRoom(long roomId);

    List<Room> getAll();

    Room createRoom(RoomDto roomDto);
}
