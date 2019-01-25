package com.igi.kinoteka_api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.model.Showing;
import com.igi.kinoteka_api.model.dto.FreshShowing;
import com.igi.kinoteka_api.model.view.View;
import com.igi.kinoteka_api.service.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/showings")
public class ShowingController {

    private ShowingService showingService;

    @Autowired
    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    @GetMapping("/movie/{movieId}")
    @JsonView(View.Open.class)
    public List<Showing> getShowingsByMovie(@PathVariable final Long movieId){
        return showingService.getByMovie(movieId);
    }

    @GetMapping("/start-date/{start}/end-date/{end}")
    @JsonView(View.Open.class)
    public List<Showing> getShowingsBetweenDates(@PathVariable LocalDateTime start, @PathVariable LocalDateTime end){
        return showingService.getBetweenDates(start, end);
    }

    @PostMapping
    @JsonView(View.Open.class)
    public Showing addShowing(@RequestBody FreshShowing freshShowing){
        return showingService.addShowing(freshShowing);
    }
}
