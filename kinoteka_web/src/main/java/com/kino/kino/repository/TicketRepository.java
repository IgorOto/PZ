package com.kino.kino.repository;

import java.util.List;

import com.kino.kino.model.Showing;
import com.kino.kino.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByShowing(Showing showing);
    Ticket findTicketBySeat_id(long seatId);
    List<Ticket> findAllByUser_id(long userId);
}
