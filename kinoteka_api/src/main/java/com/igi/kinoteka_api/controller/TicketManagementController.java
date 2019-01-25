package com.igi.kinoteka_api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.model.Ticket;
import com.igi.kinoteka_api.model.view.View;
import com.igi.kinoteka_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showings/{showingId}/tickets")
public class TicketManagementController {

    private TicketService ticketService;

    @Autowired
    public TicketManagementController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    @JsonView(View.Tic.class)
    public List<Ticket> getByShowing(@PathVariable long showingId) {
        return ticketService.getByShowing(showingId);
    }


    @PutMapping("/user/{userId}/buy")
    public List<Ticket> buy(@PathVariable long userId) {
        return ticketService.buy(userId);
    }

}
