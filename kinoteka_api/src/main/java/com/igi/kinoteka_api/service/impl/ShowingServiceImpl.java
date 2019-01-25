package com.igi.kinoteka_api.service.impl;

import com.igi.kinoteka_api.model.Movie;
import com.igi.kinoteka_api.model.Room;
import com.igi.kinoteka_api.model.Showing;
import com.igi.kinoteka_api.model.Ticket;
import com.igi.kinoteka_api.model.dto.FreshShowing;
import com.igi.kinoteka_api.repository.MovieRepository;
import com.igi.kinoteka_api.repository.RoomRepository;
import com.igi.kinoteka_api.repository.ShowingRepository;
import com.igi.kinoteka_api.repository.TicketRepository;
import com.igi.kinoteka_api.model.*;
import com.igi.kinoteka_api.service.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowingServiceImpl implements ShowingService{

    private ShowingRepository showingRepository;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public ShowingServiceImpl(ShowingRepository showingRepository, MovieRepository movieRepository, RoomRepository roomRepository, TicketRepository ticketRepository) {
        this.showingRepository = showingRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Showing> getByMovie(Long movieId) {
       Optional<Movie> dbMovie = movieRepository.findById(movieId);
       if (!dbMovie.isPresent()) throw new EntityNotFoundException("Movie with id " + movieId + " not found!");
       return showingRepository.findAllByMovie(dbMovie.get());
    }

    @Override
    public List<Showing> getBetweenDates(LocalDateTime start, LocalDateTime end) {
        return showingRepository.findAllByStartBetween(start, end);
    }

    @Override
    @Transactional
    public Showing addShowing(final FreshShowing freshShowing) {
        Movie movie = getMovieIfExsts(freshShowing);
        Room room = getRoomIfExists(freshShowing);
        checkIfShowingNotExists(freshShowing, room);

        Showing showing = Showing.builder()
                .movie(movie)
                .room(room)
                .start(freshShowing.getStart())
                .end(freshShowing.getEnd())
                .ticketPrice(freshShowing.getTicketPrice())
                .build();
        showing = showingRepository.save(showing);

        createTickets(showing, room);

        return showing;
    }

    private void checkIfShowingNotExists(FreshShowing freshShowing, Room room) {
        Optional<Showing> dbShowing = showingRepository.findByRoomAndStartGreaterThanEqualAndEndLessThanEqual(
                room,
                freshShowing.getStart(),
                freshShowing.getEnd());
        if (dbShowing.isPresent()) throw new EntityExistsException("Room occupied!");
    }

    private Room getRoomIfExists(FreshShowing freshShowing) {
        Optional<Room> dbRoom = roomRepository.findById(freshShowing.getRoomId());
        return dbRoom.orElseThrow(() -> new EntityNotFoundException("No room found!"));
    }

    private Movie getMovieIfExsts(FreshShowing freshShowing) {
        Optional<Movie> dbMovie = movieRepository.findById(freshShowing.getMovieId());
        return dbMovie.orElseThrow(() -> new EntityNotFoundException("No movie found!"));
    }

    private void createTickets(final Showing showing, final Room room) {
        room.getSeats().forEach(seat ->{
            Ticket ticket = Ticket.builder()
                    .seat(seat)
                    .showing(showing)
                    .build();
            ticketRepository.save(ticket);
        });
    }
}
