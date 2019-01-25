package com.igi.event;

import com.igi.component.model.TicketReservationRepresentation;
import lombok.Getter;

import java.util.List;

@Getter
public class MyReservationsEvent {
    private List<TicketReservationRepresentation> found;
    public MyReservationsEvent(List<TicketReservationRepresentation> found) {
        this.found = found;
    }
}
