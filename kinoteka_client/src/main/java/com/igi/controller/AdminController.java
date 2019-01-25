package com.igi.controller;

import com.google.common.eventbus.Subscribe;
import com.igi.event.*;
import com.igi.lang.ActiveLang;
import com.igi.lang.LangValue;
import com.igi.popup.Popup;
import com.igi.repository.Repository;
import com.igi.repository.model.Movie;
import com.igi.repository.model.Room;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public Label addTitleLabel;
    public Label moviesLabel;
    public Button addMovieButton;
    public Label addShowingLabel;
    public Label movieLabel;
    public Label roomLabel;
    public Label dateLabel;
    public Label startTimeLabel;
    public Label endTimeLabel;
    public Button addShowingButton;
    @FXML
    private ListView<Movie> movieListView;

    @FXML
    private TextField addMovieInput;

    @FXML
    private ChoiceBox<Movie> moviesChoiceBox;

    @FXML
    private ChoiceBox<Room> roomChoiceBox;

    @FXML
    private DatePicker showingDateInput;

    @FXML
    private TextField startTimeInput;

    @FXML
    private TextField endTimeInput;

    private Repository repository;

    private GlobalEventBus bus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        repository = Repository.get();
        bus = GlobalEventBus.getBus();
        bus.register(this);
        movieListView.setCellFactory(getCallback());
        getMovies();
        getRooms();
        refreshText();

        showingDateInput.valueProperty().addListener((ov, oldValue, newValue) ->{
            LocalDate now = LocalDate.now();
            if(newValue.isBefore(now)) showingDateInput.setValue(now);
        });
    }

    public void getMovies() {
        ObservableList<Movie> movies = repository.getMovies();
        movieListView.setItems(movies);
        moviesChoiceBox.setItems(movies);
    }

    public void addMovie() {
        if (!addMovieInput.getText().isEmpty()) {
            repository.addMovie(addMovieInput.getText());
        }
    }

    @Subscribe
    public void onMovieAdded(AddedMovieEvent e) {
        getMovies();
    }

    @Subscribe
    public void onAddShowingSucess(AddShowingSuccessEvent e){
        Popup.success(ActiveLang.getValue(LangValue.SUCCESS));
    }

    @Subscribe
    public void onAddShowingFailure(AddShowingFailureEvent e){
        Popup.failure(ActiveLang.getValue(LangValue.FAILURE));
    }

    public void addShowing() {
        LocalDate date = showingDateInput.getValue();
        String baseDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startDateTime = baseDate + "_" + startTimeInput.getText();
        String endDateTime = baseDate + "_" + endTimeInput.getText();

        repository.addShowing(moviesChoiceBox.getValue(), roomChoiceBox.getValue(), startDateTime, endDateTime);

    }

    private Callback<ListView<Movie>, ListCell<Movie>> getCallback() {
        return new Callback<ListView<Movie>, ListCell<Movie>>() {
            @Override
            public ListCell<Movie> call(ListView<Movie> param) {
                return new ListCell<Movie>() {

                    @Override
                    protected void updateItem(Movie t, boolean bin) {
                        super.updateItem(t, bin);
                        if (t != null) {
                            setText(t.getName());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        };
    }

    public void getRooms() {
        roomChoiceBox.setItems(repository.getRooms());
    }

    @Subscribe
    public void onLangChange(LangEvent event) {
        refreshText();
    }

    private void refreshText() {
        addTitleLabel.setText(ActiveLang.getValue(LangValue.ADD_TITLE));
        moviesLabel.setText(ActiveLang.getValue(LangValue.MOVIES));
        addMovieButton.setText(ActiveLang.getValue(LangValue.ADD_TITLE_BTN));
        addShowingLabel.setText(ActiveLang.getValue(LangValue.ADD_SHOWING));
        movieLabel.setText(ActiveLang.getValue(LangValue.MOVIE));
        roomLabel.setText(ActiveLang.getValue(LangValue.ROOM));
        dateLabel.setText(ActiveLang.getValue(LangValue.DATE));
        startTimeLabel.setText(ActiveLang.getValue(LangValue.START_TIME));
        endTimeLabel.setText(ActiveLang.getValue(LangValue.END_TIME));
        addShowingButton.setText(ActiveLang.getValue(LangValue.ADD_SHOWING));
    }
}
