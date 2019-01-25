package com.igi.kinoteka_api.model.dto;

import com.igi.kinoteka_api.model.Seat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RoomDto {
    private String name;
    private List<Seat> seats;
}
