package com.kino.kino.controller.api;

import com.kino.kino.model.Room;
import com.kino.kino.model.Seat;
import com.kino.kino.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.experimental.PackagePrivate;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAll(){
        return roomService.getAll();
    }

    @GetMapping("/{roomId}/seats")
    public List<Seat> getSeats(@PathVariable long roomId){
        return roomService.getSeatsFor(roomId);
    }
}
