package com.igi.controller;

import com.google.common.eventbus.Subscribe;
import com.igi.component.RoomComponent;
import com.igi.event.*;
import com.igi.lang.ActiveLang;
import com.igi.lang.LangValue;
import com.igi.popup.Popup;
import com.igi.repository.Repository;
import com.igi.repository.model.Showing;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ShowingsController implements Initializable {

    public Label fromLabel;
    public Label toLabel;
    public Label showingsLabel;
    private GlobalEventBus bus;
    private String lastReservation = "";

    private Repository repository;

    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private Button filterDate;
    @FXML
    private ListView<Showing> showingsListView;
    @FXML
    private AnchorPane showingRoomBox;
    @FXML
    private Button reservationButton;
    @FXML
    private Label legendNormal;
    @FXML
    private Label legendDiscount;

    private RoomComponent roomSeatsComponent;

    private Showing selectedShowing = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bus = GlobalEventBus.getBus();
        repository = Repository.get();
        bus.register(this);

        fromDate.setValue(LocalDate.now());
        toDate.setValue(LocalDate.now().plusDays(7));

        onFilter();

        showingsListView.setCellFactory(getCallback());
        showingsListView.setOnMouseClicked(event -> {
            Showing selectedItem = showingsListView
                    .getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                selectedShowing = selectedItem;
                
                repository.getShowingTickets(selectedItem);
            }
        });
        refreshText();

        fromDate.valueProperty().addListener((ov, oldValue, newValue) -> {
            LocalDate now = LocalDate.now();
            now.atStartOfDay();
            if(newValue.isBefore(now)) fromDate.setValue(now);
        });

        toDate.valueProperty().addListener((ov, oldValue, newValue) -> {
            LocalDate now = LocalDate.now();
            now.atTime(23, 59);
            if(newValue.isBefore(now)) toDate.setValue(now);
        });
    }

    private void refreshText() {
        fromLabel.setText(ActiveLang.getValue(LangValue.FROM));
        toLabel.setText(ActiveLang.getValue(LangValue.TO));
        filterDate.setText(ActiveLang.getValue(LangValue.SEARCH));
        showingsLabel.setText(ActiveLang.getValue(LangValue.SHOWINGS));
        reservationButton.setText(ActiveLang.getValue(LangValue.MAKE_RESERVATION));
        legendNormal.setText(ActiveLang.getValue(LangValue.TICKET_NORMAL));
        legendDiscount.setText(ActiveLang.getValue(LangValue.TICKET_DISCOUNT));
    }

    public void onFilter() {
        LocalDate from = fromDate.getValue();
        LocalDate to = toDate.getValue();
        showingsListView.setItems(repository.getShowingsByTimePeriod(from.atStartOfDay(), to.atTime(23, 59)));
    }

    @Subscribe
    public void onShowingSelected(ShowingRoomSelectedEvent event){
       
        showingRoomBox.getChildren().clear();
        roomSeatsComponent = new RoomComponent(event.getTicketRepresentations());
        showingRoomBox.getChildren().add(roomSeatsComponent);
        reservationButton.setDisable(false);

    }

    public void makeReservation(){
        if(roomSeatsComponent != null && roomSeatsComponent.getSelectedTickets().size() > 0){
            repository.makeReservation(roomSeatsComponent.getSelectedTickets());
        }
    }

    @Subscribe
    public void onReservationSuccess(ReservationSuccessEvent event){
        

        if(selectedShowing!= null) repository.getShowingTickets(selectedShowing);
    }

    @Subscribe
    public void onReservationFailure(ReservationFailureEvent event){
        Popup.failure(ActiveLang.getValue(LangValue.FAILURE));
        if(selectedShowing!= null) repository.getShowingTickets(selectedShowing);
    }

    private Callback<ListView<Showing>, ListCell<Showing>> getCallback() {
        return new Callback<ListView<Showing>, ListCell<Showing>>() {
            @Override
            public ListCell<Showing> call(ListView<Showing> param) {
                return new ListCell<Showing>() {

                    @Override
                    protected void updateItem(Showing t, boolean bin) {
                        super.updateItem(t, bin);
                        if (t != null) {
                            setText(t.getMovie().getName() + " " + ActiveLang.getValue(LangValue.ROOM) +" " + t.getRoom().getName() +  " " + t.getStart());
                        }else {
                            setText(null);
                        }
                    }
                };
            }
        };
    }

    @Subscribe
    public void onLangChange(LangEvent event) {
       refreshText();
    }
}
