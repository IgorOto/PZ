package com.igi.kinoteka_api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.model.view.View;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ticket extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_showing", nullable = false)
    @JsonView(value = {View.Disabled.class, View.Res.class})
    protected Showing showing;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_seat", nullable = false)
    @JsonView(value = {View.Tic.class, View.Res.class})
    protected Seat seat;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "fk_user", nullable = true)
    @JsonView(value = {View.Disabled.class})
    @Builder.Default
    protected User user = null;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @JsonView(value = {View.Disabled.class, View.Tic.class})
    protected Status status = Status.AVAILABLE;

    @Builder.Default
    @JsonView(value = {View.Res.class})
    protected boolean discount = false;

    public enum Status{
        AVAILABLE,
        RESERVED,
        SOLD;
    }

}
