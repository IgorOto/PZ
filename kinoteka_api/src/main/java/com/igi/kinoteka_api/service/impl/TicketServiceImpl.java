package com.igi.kinoteka_api.service.impl;

import com.igi.kinoteka_api.model.Showing;
import com.igi.kinoteka_api.model.dto.TicketDto;
import com.igi.kinoteka_api.model.dto.TicketId;
import com.igi.kinoteka_api.repository.UserRepository;
import com.igi.kinoteka_api.model.Ticket;
import com.igi.kinoteka_api.model.User;
import com.igi.kinoteka_api.repository.ShowingRepository;
import com.igi.kinoteka_api.repository.TicketRepository;
import com.igi.kinoteka_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private ShowingRepository showingRepository;
    private UserRepository userRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ShowingRepository showingRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.showingRepository = showingRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> getByShowing(long showingId) {
        Optional<Showing> dbShowing = showingRepository.findById(showingId);
        if(!dbShowing.isPresent()) throw new EntityNotFoundException("Showing with id " + showingId + " not exists!");
        return ticketRepository.findAllByShowing(dbShowing.get());
    }

    @Override
    public Optional<Ticket> getById(long id) {
        return ticketRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = {EntityNotFoundException.class, IllegalStateException.class})
    public List<Ticket> makeReservation(long userId, List<TicketDto> ticketIds) {
        List<Ticket> reserved = new ArrayList<>();
        User user = getUserIfExists(userId);

        for(TicketDto ticketDto : ticketIds){
            Optional<Ticket> dbTicket = ticketRepository.findById(ticketDto.getId());
            if(!dbTicket.isPresent()){
                throw new EntityNotFoundException("Ticket with id " + ticketDto.getId() + " not found!");
            }
            Ticket ticket = dbTicket.get();
            User assignedUser = ticket.getUser();
            if(assignedUser != null){
               throw new IllegalStateException("Ticket" + ticketDto.getId() + " not available!");
            }
            else{
                ticket.setStatus(Ticket.Status.RESERVED);
                ticket.setUser(user);
                ticket.setDiscount(ticketDto.isDiscount());
                ticketRepository.save(ticket);
                reserved.add(ticket);
            }
            
        }
        return reserved;
    }

    @Override
    @Transactional
    public List<Ticket> buy(long userId) {
        User user = getUserIfExists(userId);
        return ticketRepository.findAllByUserAndStatus(user, Ticket.Status.RESERVED)
                .stream()
                .peek(ticket -> ticket.setStatus(Ticket.Status.SOLD))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Ticket> getTicketsByUserId(long userId) {
        List<Ticket> tickets = new ArrayList<>();
        Optional<User> dbUser = userRepository.findById(userId);
        if(dbUser.isPresent()){
            tickets = ticketRepository.findAllByUser(dbUser.get());
        }

        return tickets;
    }

    @Override
    @Transactional
    public List<Ticket> deleteReservation(long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        User user = null;
        if(ticket.isPresent()){
            Ticket ticket1 = ticket.get();
            user = ticket1.getUser();
            ticket1.setStatus(Ticket.Status.AVAILABLE);
            ticket1.setUser(null);
            ticketRepository.save(ticket1);
        }
        if(user == null) return new ArrayList<>();
        return ticketRepository.findAllByUser(user);
    }

    private User getUserIfExists(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User with id " + userId + " not found!"));
    }
}
