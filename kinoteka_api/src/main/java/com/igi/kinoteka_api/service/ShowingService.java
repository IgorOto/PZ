package com.igi.kinoteka_api.service;

import com.igi.kinoteka_api.model.Showing;
import com.igi.kinoteka_api.model.dto.FreshShowing;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowingService {
    List<Showing> getByMovie(Long movieId);

    List<Showing> getBetweenDates(LocalDateTime start, LocalDateTime end);

    Showing addShowing(FreshShowing freshShowing);
}
