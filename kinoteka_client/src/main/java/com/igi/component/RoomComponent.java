package com.igi.component;

import com.igi.component.model.Row;
import com.igi.component.model.TicketSeatRepresentation;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RoomComponent extends GridPane {

    protected final Logger log = Logger.getLogger(getClass().getName());

    private List<TicketSeatRepresentation> ticketRepresentations;
    @Getter
    private List<TicketSeatRepresentation> selectedTickets;

    public RoomComponent(List<TicketSeatRepresentation> ticketRepresentations) {
        super();
        this.ticketRepresentations = ticketRepresentations;
        this.selectedTickets = new ArrayList<>();
        draw();
        log.info("init");
    }

    private void draw() {
        this.add(new Separator(), 0, 0, 10, 10);
        for (TicketSeatRepresentation ticket : ticketRepresentations) {
            SeatButtonComponent button = new SeatButtonComponent(ticket);



            this.add(button, ticket.getSeat().getSeatNumber() + 1, Row.valueOf(ticket.getSeat().getRow()).getRowNumber() + 1, 1, 1);
            button.setOnAction(e -> {
                SeatButtonComponent that = (SeatButtonComponent) e.getSource();

                if(that.getState() == SeatButtonComponent.State.AVAILABLE){
                    that.setState(SeatButtonComponent.State.SELECTED);
                    that.setStyle(SeatButtonComponent.SELECTED_STYLE);
                    that.getTicket().setDiscount(false);
                    selectedTickets.add(that.getTicket());
                } else if(that.getState() == SeatButtonComponent.State.SELECTED){
                    that.setState(SeatButtonComponent.State.SELECTED_DISCOUNT);
                    that.setStyle(SeatButtonComponent.SELECTED_DISCOUNT_STYLE);
                    //selectedTickets.remove(that.getTicket());
                    that.getTicket().setDiscount(true);
                    //selectedTickets.add(that.getTicket());

                } else{
                    that.setState(SeatButtonComponent.State.AVAILABLE);
                    that.setStyle(SeatButtonComponent.AVAILABLE_STYLE);
                    that.getTicket().setDiscount(false);
                    selectedTickets.remove(that.getTicket());
                }
            });
        }
    }

}
