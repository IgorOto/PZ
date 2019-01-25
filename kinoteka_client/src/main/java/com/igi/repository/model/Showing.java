package com.igi.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Showing {
    private long id;
    private Movie movie;
    private String start;
    private String end;
    private Room room;
    private int ticketPrice;
}
