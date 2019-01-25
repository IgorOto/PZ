package com.igi.kinoteka_api.repository;

import com.igi.kinoteka_api.model.Showing;
import com.igi.kinoteka_api.model.Ticket;
import com.igi.kinoteka_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByShowing(Showing showing);

    List<Ticket> findAllByUserAndStatus(User user, Ticket.Status status);

    List<Ticket> findAllByUser(User user);
}
