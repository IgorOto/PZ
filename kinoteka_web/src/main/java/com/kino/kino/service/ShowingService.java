package com.kino.kino.service;

import java.time.LocalDateTime;
import java.util.List;

import com.kino.kino.model.Showing;
import com.kino.kino.model.Ticket;
import com.kino.kino.model.dto.AddShowingDto;
import com.kino.kino.model.dto.AddTicketsDto;

public interface ShowingService {
    Showing add(AddShowingDto showing);

	List<Showing> getAll();

	void delete(Long showingId);

	List<Showing> findByMovieAfter(long movieId, LocalDateTime date);

	List<Showing> getByLangAndDate(String langVal, LocalDateTime start, LocalDateTime end);

	List<Ticket> getTicketsByShowing(long showingId);

	List<Ticket> addUserTicketsToShowing(long showingId, AddTicketsDto ticketsDto);

	List<Ticket> getTicketsByUser(long userId);

	void deleteTicket(long id);

	Ticket getTicket(long id);
}
