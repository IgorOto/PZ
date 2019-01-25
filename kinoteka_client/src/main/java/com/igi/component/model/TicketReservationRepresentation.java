package com.igi.component.model;

import com.igi.repository.model.Showing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketReservationRepresentation {
    private long id;
    private Showing showing;
    private SeatRepresentation seat;
    private boolean discount;

    @Override
    public String toString() {
        return showing.getMovie().getName() + " " +showing.getStart() + " " + showing.getRoom().getName() + " " + seat.getRow() + seat.getSeatNumber();
    }
}
