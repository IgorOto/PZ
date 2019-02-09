package com.kino.kino.controller.api;

import com.kino.kino.model.Room;
import com.kino.kino.model.Seat;
import com.kino.kino.model.Ticket;
import com.kino.kino.service.RoomService;
import com.kino.kino.service.ShowingService;
import com.kino.kino.util.PdfGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class TicketController {

    ShowingService showingService;

    @Autowired
    public TicketController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @GetMapping("/api/ticket/user/{userId}")
    public List<Ticket> getTicketsByUser(@PathVariable long userId) {
        return showingService.getTicketsByUser(userId);
    }

    @DeleteMapping("/api/ticket/{id}")
    public void deleteTicket(@PathVariable long id) {
        showingService.deleteTicket(id);
    }

    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport(@PathVariable long id) throws IOException {

        Ticket ticket = showingService.getTicket(id);

        ByteArrayInputStream bis = PdfGenerator.ticket(ticket);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ticket-print.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
