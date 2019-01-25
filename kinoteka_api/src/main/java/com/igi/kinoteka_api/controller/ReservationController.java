package com.igi.kinoteka_api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.model.Ticket;
import com.igi.kinoteka_api.model.dto.TicketDto;
import com.igi.kinoteka_api.model.dto.TicketId;
import com.igi.kinoteka_api.model.view.View;
import com.igi.kinoteka_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.logging.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    protected final Logger log = Logger.getLogger(getClass().getName()); //java.util.logging.Logger

    private TicketService ticketService;

    @Autowired
    public ReservationController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/user/{userId}")
    @JsonView(View.Res.class)
    public List<Ticket> getMy(@PathVariable long userId) {
        return ticketService.getTicketsByUserId(userId);
    }

    @PutMapping("/user/{userId}")
    @JsonView(View.Tic.class)
    public List<Ticket> makeReservation(@PathVariable long userId, @RequestBody List<TicketDto> ticketIds) {
        
        log.log(Level.INFO, ticketIds.toString());
        return ticketService.makeReservation(userId, ticketIds);
    }

    @PutMapping("/{ticketId}")
    @JsonView(View.Res.class)
    public List<Ticket> deleteReservation(@PathVariable long ticketId) {
        return ticketService.deleteReservation(ticketId);
    }
}
