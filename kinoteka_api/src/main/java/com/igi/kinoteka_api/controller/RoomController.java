package com.igi.kinoteka_api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.service.RoomSeatService;
import com.igi.kinoteka_api.model.Room;
import com.igi.kinoteka_api.model.dto.RoomDto;
import com.igi.kinoteka_api.model.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomSeatService roomSeatService;

    @Autowired
    public RoomController(RoomSeatService roomSeatService) {
        this.roomSeatService = roomSeatService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Room addRoom(@RequestBody final RoomDto roomDto){
        return roomSeatService.createRoom(roomDto);
    }

    @GetMapping("/{roomId}")
    @JsonView(View.Full.class)
    public Room getRoom(@PathVariable final long roomId){
        return roomSeatService.getRoom(roomId);
    }

    @GetMapping
    @JsonView(View.Open.class)
    public List<Room> getAll(){
        return roomSeatService.getAll();
    }
}
