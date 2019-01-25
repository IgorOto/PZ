package com.igi.kinoteka_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.model.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seat extends BaseEntity{

    @JsonView(value = {View.Open.class, View.Tic.class, View.Res.class})
    protected String row;

    @JsonView(value = {View.Open.class, View.Tic.class, View.Res.class})
    protected int seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_room")
    @JsonIgnore
    protected Room room;
}
