package com.igi.kinoteka_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.model.view.View;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Showing extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_movie")
    @JsonView(value = {View.Open.class, View.Res.class})
    protected Movie movie;

    @JsonView(value = {View.Open.class, View.Res.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    protected LocalDateTime start;

    @JsonView(View.Open.class)
    protected LocalDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_room")
    @JsonView(value = {View.Open.class, View.Res.class})
    protected Room room;

    @JsonView(View.Open.class)
    protected int ticketPrice;
}
