package com.igi.component;

import com.igi.component.model.TicketSeatRepresentation;
import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SeatButtonComponent extends Button {
    public static final String AVAILABLE_STYLE = "-fx-background-color: #00ffcb; -fx-text-fill: black;";
    public static final String SELECTED_STYLE = "-fx-background-color: #00e9ff; -fx-text-fill: black;";
    public static final String SELECTED_DISCOUNT_STYLE = "-fx-background-color: #00ff00; -fx-text-fill: black;";
    public static final String OCCUPIED_STYLE = "-fx-background-color: #ffb600; -fx-text-fill: black; -fx-opacity: 1;";
    private TicketSeatRepresentation ticket;
    @Setter
    private State state = State.AVAILABLE;

    public static enum State{
        AVAILABLE,
        SELECTED,
        SELECTED_DISCOUNT;
    }

    public SeatButtonComponent(TicketSeatRepresentation ticket) {
        super();
        this.ticket = ticket;
        draw();
    }

    private void draw() {
        this.setText(ticket.getSeat().getRow() + ticket.getSeat().getSeatNumber());
        if (!ticket.getStatus().equals("AVAILABLE")){
            this.setDisable(true);
            this.setStyle(OCCUPIED_STYLE);
            return;
        }
        this.setStyle(AVAILABLE_STYLE);
    }
}
