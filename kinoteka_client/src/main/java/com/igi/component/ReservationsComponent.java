package com.igi.component;

import com.igi.component.model.TicketReservationRepresentation;
import com.igi.repository.Repository;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.logging.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.List;

public class ReservationsComponent extends VBox {
    protected final Logger log = Logger.getLogger(getClass().getName());

    public static Repository repository = Repository.get();

    private String normalName;
    private String discountName;

    public ReservationsComponent(List<TicketReservationRepresentation> tickets, String normalName, String discountName){
        this.normalName = normalName;
        this.discountName = discountName;
        this.setStyle("-fx-padding: 60 0 0 0");

        this.setSpacing(16);

        for(TicketReservationRepresentation ticket : tickets){
            HBox hBox = new HBox();
            hBox.setSpacing(8);
            hBox.setStyle("-fx-background-color: #777777;-fx-opacity: 0.8;");
            hBox.setPadding(new Insets(10));
			
			
        
        
		hBox.setAlignment(Pos.BASELINE_LEFT);
		Pane pane = new Pane();
		HBox.setHgrow(pane, Priority.ALWAYS);
		
			
			
            log.log(Level.INFO, ticket.toString());
            Label label = new Label(ticket.toString() + " " + (ticket.isDiscount()? discountName : normalName));
            label.setStyle("-fx-text-fill: white;");
            Button deleteBtn = new Button("X");
            deleteBtn.setOnAction(e -> {
                repository.deleteTicket(ticket.getId());
                log.log(Level.INFO, String.valueOf(ticket.getId()));
            });
            hBox.getChildren().add(label);
			hBox.getChildren().add(pane);
            hBox.getChildren().add(deleteBtn);
            this.getChildren().add(hBox);
        }

    }
}
