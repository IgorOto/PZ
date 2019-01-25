package com.igi.event;

import com.igi.component.model.TicketSeatRepresentation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ShowingRoomSelectedEvent {
    private List<TicketSeatRepresentation> ticketRepresentations;
}
