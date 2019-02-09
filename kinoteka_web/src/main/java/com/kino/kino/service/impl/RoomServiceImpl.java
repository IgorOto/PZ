package com.kino.kino.service.impl;

import com.kino.kino.model.Room;
import com.kino.kino.model.Seat;
import com.kino.kino.repository.RoomRepository;
import com.kino.kino.repository.SeatRepository;
import com.kino.kino.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    RoomRepository roomRepository;
    SeatRepository seatRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, SeatRepository seatRepository) {
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public List<Seat> getSeatsFor(long roomId) {
        return seatRepository.findAllByRoom_id(roomId);
    }

    
}
