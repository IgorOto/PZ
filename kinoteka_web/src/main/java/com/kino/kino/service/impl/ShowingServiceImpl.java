package com.kino.kino.service.impl;

import com.kino.kino.model.Movie;
import com.kino.kino.model.Room;
import com.kino.kino.model.Seat;
import com.kino.kino.model.Showing;
import com.kino.kino.model.Ticket;
import com.kino.kino.model.User;
import com.kino.kino.model.dto.AddShowingDto;
import com.kino.kino.model.dto.AddTicketDto;
import com.kino.kino.model.dto.AddTicketsDto;
import com.kino.kino.repository.MovieRepository;
import com.kino.kino.repository.RoomRepository;
import com.kino.kino.repository.SeatRepository;
import com.kino.kino.repository.ShowingRepository;
import com.kino.kino.repository.TicketRepository;
import com.kino.kino.repository.UserRepository;
import com.kino.kino.service.ShowingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowingServiceImpl implements ShowingService {

    ShowingRepository showingRepository;
    MovieRepository movieRepository;
    RoomRepository roomRepository;
    TicketRepository ticketRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;
    SeatRepository seatRepository;

    @Autowired
    public ShowingServiceImpl(ShowingRepository showingRepository, MovieRepository movieRepository,
            RoomRepository roomRepository, TicketRepository ticketRepository, ModelMapper modelMapper, 
            UserRepository userRepository, SeatRepository seatRepository) {
        this.showingRepository = showingRepository;
        this.roomRepository = roomRepository;
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional
    public Showing add(AddShowingDto showingDto) {
        LocalDateTime now = LocalDateTime.now();
        if (showingDto.parseDateTime().isBefore(now))
            throw new IllegalArgumentException("Showing date must be in the future!");
        Movie movie = movieRepository.getOne(showingDto.getMovieId());
        Room room = roomRepository.getOne(showingDto.getRoomId());
        if (movie == null || room == null)
            throw new EntityNotFoundException("Movie or Room not found!");
        LocalDateTime startDateTime = showingDto.parseDateTime();
        LocalDateTime endDateTime = startDateTime.plusMinutes(new Long(movie.getMinutes()));
        List<Showing> dbShowings = showingRepository.findAllByRoomAndDatetimeBetweenOrRoomAndEndDatetimeBetween(room,
                startDateTime, endDateTime, room, startDateTime, endDateTime);
        if (dbShowings.size() > 0) {
            System.out.println("other showings found!");
            throw new IllegalArgumentException("Room occupied!");
        }
        Showing showing = new Showing();
        showing.setDatetime(startDateTime);
        showing.setEndDatetime(endDateTime);
        showing.setMovie(movie);
        showing.setRoom(room);
        return showingRepository.save(showing);
    }

    @Override
    public List<Showing> getAll() {
        return showingRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long showingId) {
        Showing showing = showingRepository.getOne(showingId);
        if (showing == null)
            return;
        if (ticketRepository.findAllByShowing(showing).size() > 0)
            return;
        showingRepository.deleteById(showingId);
    }

    @Override
    public List<Showing> findByMovieAfter(long movieId, LocalDateTime date) {
        Optional<Movie> dbMovie = movieRepository.findById(movieId);
        if (!dbMovie.isPresent())
            return new ArrayList<>();
        return showingRepository.findAllByMovieAndDatetimeAfterOrderByDatetime(dbMovie.get(), date);
    }

    @Override
    public List<Showing> getByLangAndDate(String langVal, LocalDateTime start, LocalDateTime end) {
        return showingRepository.findAllByDatetimeBetweenOrderByMovie(start, end);
    }

    @Override
    @Transactional
    public List<Ticket> getTicketsByShowing(long showingId) {
        Optional<Showing> showing = showingRepository.findById(showingId);
        if (!showing.isPresent())
            return new ArrayList<>();
        return ticketRepository.findAllByShowing(showing.get());
    }

    @Override
    @Transactional
    public List<Ticket> addUserTicketsToShowing(long showingId, AddTicketsDto ticketsDto) {
        Optional<Showing> showing = showingRepository.findById(showingId);
        if(!showing.isPresent()) throw new EntityNotFoundException("Showing not found!");
        long userId = ticketsDto.getUserId();
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) throw new EntityNotFoundException("User not found!");
        List<AddTicketDto> ticketsDtolist = ticketsDto.getTickets();
        List<Ticket> ticketsToBuy = new ArrayList<>();
        for(AddTicketDto dto : ticketsDtolist){
            if(ticketRepository.findTicketBySeat_id(
                dto.getSeatId()) != null) throw new EntityExistsException("Ticket already bought!");

            Optional<Seat> seat = seatRepository.findById(dto.getSeatId());
            if(!seat.isPresent()) throw new EntityNotFoundException("Seat not found!");

            Ticket freshTicket = Ticket.builder()
                .seat(seat.get())
                .showing(showing.get())
                .user(user.get())
                .type(dto.getTicketType())
                .build();
                ticketsToBuy.add(freshTicket);
        }

        return ticketRepository.saveAll(ticketsToBuy);
    }

    @Override
    public List<Ticket> getTicketsByUser(long userId) {
        return ticketRepository.findAllByUser_id(userId);
    }

    @Override
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket getTicket(long id) {
        return ticketRepository.getOne(id);
    }
}
