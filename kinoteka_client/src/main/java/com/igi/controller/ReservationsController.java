package com.igi.controller;

import com.google.common.eventbus.Subscribe;
import com.igi.component.ReservationsComponent;
import com.igi.component.model.TicketReservationRepresentation;
import com.igi.event.GlobalEventBus;
import com.igi.event.LangEvent;
import com.igi.event.MyReservationsEvent;
import com.igi.lang.ActiveLang;
import com.igi.lang.Lang;
import com.igi.lang.LangValue;
import com.igi.repository.Repository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.*;

public class ReservationsController implements Initializable {

    protected final Logger log = Logger.getLogger(getClass().getName()); //java.util.logging.Logger

    private Repository repository;

    private List<TicketReservationRepresentation> myReservations;

    private GlobalEventBus bus;

    @FXML
    private AnchorPane reservationsBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        repository = Repository.get();
        bus = GlobalEventBus.getBus();
        bus.register(this);
        getMyReservations();

        
    }

    @Subscribe
    public void onReservationsResponse(MyReservationsEvent e){

        myReservations = e.getFound();

        refreshReservationsView();
    }

    @Subscribe
    public void onLandEvent(LangEvent e){
        refreshReservationsView();
    }


    private void refreshReservationsView(){
        reservationsBox.getChildren().clear();
		
        Label label =new Label();
        label.setStyle("-fx-padding: 10 10 10 50; -fx-text-fill: white; -fx-font-size: 30px;");
        label.setText(ActiveLang.getValue(LangValue.MY_RES));
        reservationsBox.getChildren().add(label);
		
		
		
        reservationsBox.getChildren().add(new ReservationsComponent(myReservations, ActiveLang.getValue(LangValue.TICKET_NORMAL), ActiveLang.getValue(LangValue.TICKET_DISCOUNT)));
    }

    public void getMyReservations() {
        repository.getMyReservations();
    }


}
