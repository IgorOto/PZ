package com.igi.kinoteka_api.service;

import com.igi.kinoteka_api.model.Ticket;
import com.igi.kinoteka_api.model.dto.TicketDto;
import com.igi.kinoteka_api.model.dto.TicketId;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    List<Ticket> getByShowing(long showingId);

    Optional<Ticket> getById(long id);

    List<Ticket> makeReservation(long userId, List<TicketDto> ticketIds);

    List<Ticket> buy(long userId);

    List<Ticket> getTicketsByUserId(long userId);

    List<Ticket> deleteReservation(long ticketId);
}
