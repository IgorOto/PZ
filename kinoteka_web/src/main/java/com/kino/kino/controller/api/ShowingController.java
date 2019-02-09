package com.kino.kino.controller.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.kino.kino.model.Movie;
import com.kino.kino.model.Showing;
import com.kino.kino.model.Ticket;
import com.kino.kino.model.dto.AddShowingDto;
import com.kino.kino.model.dto.AddTicketsDto;
import com.kino.kino.service.MovieService;
import com.kino.kino.service.RoomService;
import com.kino.kino.service.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/showing")
public class ShowingController {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy_HH:mm");

    ShowingService showingService;

    @Autowired
    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Showing add(@RequestBody AddShowingDto showingDto) {
        return showingService.add(showingDto);
    }

    // url}/query/movie/${movieId}/after/${today}`;
    @GetMapping("query/movie/{movieId}/after/{day}/{month}/{year}")
    public List<Showing> getByMovieAfter(@PathVariable long movieId, @PathVariable String day,
            @PathVariable String month, @PathVariable String year) {
        String dateString = new StringBuilder().append(day).append("/").append(month).append("/").append(year)
                .append("_00:00").toString();
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);
        return showingService.findByMovieAfter(movieId, date);
    }

    @GetMapping
    public List<Showing> getAll() {
        return showingService.getAll();
    }

    @GetMapping("query/lang/{langVal}/date/{day}/{month}/{year}")
    public List<Showing> getByDate(@PathVariable String langVal, @PathVariable String day, @PathVariable String month, @PathVariable String year) {
        String startString = new StringBuilder().append(day).append("/").append(month).append("/").append(year)
                .append("_00:00").toString();
        String endString = new StringBuilder().append(day).append("/").append(month).append("/").append(year)
                .append("_23:59").toString();

        LocalDateTime start = LocalDateTime.parse(startString, formatter);
        LocalDateTime end = LocalDateTime.parse(endString, formatter);
        return showingService.getByLangAndDate(langVal, start, end);
    }

    @DeleteMapping("{showingId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long showingId) {
        showingService.delete(showingId);
    }

    @GetMapping("{showingId}/tickets")
    public List<Ticket> getTicketsByShowing(@PathVariable long showingId){
        return showingService.getTicketsByShowing(showingId);
    }

    @PostMapping("{showingId}/tickets")
    public List<Ticket> addUserTicketsByShowing(@PathVariable long showingId, @RequestBody AddTicketsDto ticketsDto){
        return showingService.addUserTicketsToShowing(showingId, ticketsDto);
    }
}
