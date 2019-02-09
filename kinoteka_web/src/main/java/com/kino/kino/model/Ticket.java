package com.kino.kino.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ticket extends BaseEntity {
    public static final String STATUS_OCCUPIED = "OCCUPIED", STATUS_BOUGHT = "BOUGHT";

    String status;
    String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showing_id")
    Showing showing;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    Seat seat;
}
