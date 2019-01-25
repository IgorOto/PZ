package com.igi.component.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TicketSeatRepresentation {
    private long id;
    private SeatRepresentation seat;
    private String status;
    private boolean discount;
}
