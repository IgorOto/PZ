package com.igi.kinoteka_api.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.igi.kinoteka_api.converter.FreshShowingDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(using = FreshShowingDeserializer.class)
public class FreshShowing {
    private long movieId;
    private long roomId;
    private LocalDateTime start;
    private LocalDateTime end;
    private int ticketPrice;
}
