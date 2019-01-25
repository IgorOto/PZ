package com.igi.kinoteka_api.service.impl;

import com.igi.kinoteka_api.repository.RoomRepository;
import com.igi.kinoteka_api.repository.SeatRepository;
import com.igi.kinoteka_api.service.RoomSeatService;
import com.igi.kinoteka_api.model.Room;
import com.igi.kinoteka_api.model.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class RoomSeatServiceImpl implements RoomSeatService {

    private RoomRepository roomRepository;
    private SeatRepository seatRepository;

    @Autowired
    public RoomSeatServiceImpl(RoomRepository roomRepository, SeatRepository seatRepository) {
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public Room getRoom(long roomId) {
        return roomRepository.getOne(roomId);
    }

    @Override
    public List<Room> getAll(){
        return roomRepository.findAll();
    }

    @Override
    public Room createRoom(RoomDto roomDto) {
        String name = roomDto.getName();
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Room's name cannot be empty!");
        Room dbRoom = roomRepository.findByName(name);
        if(dbRoom != null){
            throw new EntityExistsException("Room with name " + name + " already exists!");
        }
        Room.RoomBuilder roomBuilder = Room.builder()
                .name(name);
        if(roomDto.getSeats() != null) roomBuilder.seats(roomDto.getSeats());
        return roomRepository.save(roomBuilder.build());
    }
}
