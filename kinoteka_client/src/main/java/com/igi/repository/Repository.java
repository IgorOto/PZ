package com.igi.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igi.component.model.TicketReservationRepresentation;
import com.igi.component.model.TicketSeatRepresentation;
import com.igi.event.*;
import com.igi.repository.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class Repository {

    protected final Logger log = Logger.getLogger(getClass().getName());

    private static final String AUTH_URL = "http://localhost:8080/api/auth";
    private static final String BASE_URL = "http://localhost:8080/api/";

    private static Repository INSTANCE;
    private Status status = Status.LOGGED_OUT;
    private AuthApiDataSource api;
    private UserAuth userAuth;
    private Gson gson;
    private GlobalEventBus bus;

    private Repository() {
        gson = new Gson();
        api = new AuthApiDataSource();
        bus = GlobalEventBus.getBus();
    }

    public static Repository get() {
        if(INSTANCE == null) INSTANCE = new Repository();
        return INSTANCE;
    }

    public void logIn(String username, String password) {
        api.login(username, password, AUTH_URL, new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                if (json != null) {
                    userAuth = gson.fromJson(json, UserAuth.class);
                    status = Status.LOGGED_IN;
                    bus.post(new LoginEvent(true, userAuth));
                }
            }

            @Override
            public void onFailure(String json) {
                bus.post(new LoginEvent(false, userAuth));
            }
        });
    }


    public void logout() {
        this.userAuth = null;
        this.status = Status.LOGGED_OUT;

    }

    public ObservableList<Showing> getShowingsByTimePeriod(LocalDateTime start, LocalDateTime end) {
        final ObservableList<Showing> viewList = FXCollections.observableList(new ArrayList<>());

        String startString = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
        String endString = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));

        api.get(BASE_URL + "showings/start-date/" + startString + "/end-date/" + endString,
                new ApiDataSourceCallback() {
                    @Override
                    public void onSuccess(String json) {
                        Type complexType = new TypeToken<ArrayList<Showing>>() {
                        }.getType();
                        List<Showing> foundShowings = new Gson().fromJson(json, complexType);
                        viewList.setAll(foundShowings);

                    }

                    @Override
                    public void onFailure(String json) {
                       log.log(Level.WARNING, "onFailure");
                    }
                });

        return viewList;
    }

    public void getShowingTickets(Showing selectedItem) {
        String url = BASE_URL + "showings/" + selectedItem.getId() + "/tickets";
        api.get(url, new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                Type complexType = new TypeToken<ArrayList<TicketSeatRepresentation>>() {
                }.getType();
                List<TicketSeatRepresentation> tickets = new Gson().fromJson(json, complexType);
                bus.post(new ShowingRoomSelectedEvent(tickets));
            }

            @Override
            public void onFailure(String json) {

            }
        });
    }

    public void makeReservation(List<TicketSeatRepresentation> selectedTickets) {
        log.info("Reposiotry:makeReservation()");
        List<TicketDto> ticketDtos = new ArrayList<>();
        for(TicketSeatRepresentation ticket : selectedTickets) ticketDtos.add(new TicketDto(ticket.getId(), ticket.isDiscount()));
        //TicketIds ticketIds = new TicketIds(ticketDtos);
        String url = BASE_URL + "reservations/user/" + userAuth.getId();
        log.info("Reposiotry:makeReservation() url " + url);
        String json = gson.toJson(ticketDtos);
        api.put(url, json, new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                log.info("Reposiotry:makeReservation() onSucess body " + json);
                bus.post(new ReservationSuccessEvent(json));
            }

            @Override
            public void onFailure(String json) {
                log.warning("Reposiotry:makeReservation() onFailure body " + json);
                bus.post(new ReservationFailureEvent());
            }
        });
    }

    public ObservableList<Movie> getMovies() {
        final ObservableList<Movie> viewList = FXCollections.observableList(new ArrayList<>());
        String url = BASE_URL + "movies";
        api.get(url, new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                Type complexType = new TypeToken<ArrayList<Movie>>() {
                }.getType();
                List<Movie> foundMovies = new Gson().fromJson(json, complexType);
                viewList.setAll(foundMovies);
            }

            @Override
            public void onFailure(String json) {
                log.warning("Reposiotry: onFailure body " + json);
            }
        });
        return viewList;
    }

    public void addMovie(String text) {
        String url = BASE_URL +"movies";
        String json = gson.toJson(Movie.builder().name(text).build());
        api.post(url, json, new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                bus.post(new AddedMovieEvent());
            }

            @Override
            public void onFailure(String json) {
                log.warning("Reposiotry: onFailure body " + json);
            }
        });
    }

    public ObservableList<Room> getRooms() {
        final ObservableList<Room> viewList = FXCollections.observableList(new ArrayList<>());
        String url = BASE_URL + "rooms";
        api.get(url, new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                Type complexType = new TypeToken<ArrayList<Room>>() {
                }.getType();
                List<Room> foudRooms = new Gson().fromJson(json, complexType);
                viewList.setAll(foudRooms);
            }

            @Override
            public void onFailure(String json) {
                log.warning("Reposiotry: onFailure body " + json);
            }
        });
        return viewList;
    }

    public void addShowing(Movie movie, Room room, String startDateTime, String endDateTime) {
        FreshShowing showing = FreshShowing.builder()
                .movieId(movie.getId())
                .roomId(room.getId())
                .start(startDateTime)
                .end(endDateTime)
                .build();
        String url = BASE_URL + "showings";
        String json = gson.toJson(showing);
        api.post(url, json, new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                bus.post(new AddShowingSuccessEvent());
            }

            @Override
            public void onFailure(String json) {
                bus.post(new AddShowingFailureEvent());
            }
        });
    }

    public void getMyReservations() {
        String url = BASE_URL + "reservations/user/" + userAuth.getId();
        api.get(url, new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                Type complexType = new TypeToken<ArrayList<TicketReservationRepresentation>>() {
                }.getType();
                
                List<TicketReservationRepresentation> found = new Gson().fromJson(json, complexType);

                bus.post(new MyReservationsEvent(found));
            }

            @Override
            public void onFailure(String json) {
                log.warning("Reposiotry: onFailure body " + json);
            }
        });
    }

    public void register(UserDto userDto) {
        String url = BASE_URL + "user";
        api.register(url, gson.toJson(userDto), new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                log.info("Zarejestrowano");
                bus.post(new RegisterEvent(true));
            }

            @Override
            public void onFailure(String json) {
                log.warning("NIE Zarejestrowano");
                bus.post(new RegisterEvent(false));
            }
        });
    }

    public void deleteTicket(long id) {
        String url = BASE_URL + "reservations/" + id;
        api.put(url, "{}", new ApiDataSourceCallback() {
            @Override
            public void onSuccess(String json) {
                Type complexType = new TypeToken<ArrayList<TicketReservationRepresentation>>() {
                }.getType();
                 
                List<TicketReservationRepresentation> found = new Gson().fromJson(json, complexType);

                bus.post(new MyReservationsEvent(found));
            }

            @Override
            public void onFailure(String json) {
                log.warning("Reposiotry: onFailure body " + json);
            }
        });
    }

    public enum Status {
        LOGGED_IN,
        LOGGED_OUT;
    }

}
