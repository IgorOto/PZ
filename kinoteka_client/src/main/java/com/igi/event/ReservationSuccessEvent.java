package com.igi.event;

public class ReservationSuccessEvent {
    private String json;

    public ReservationSuccessEvent(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

}
