package com.igi.repository.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FreshShowing {
    private long movieId;
    private long roomId;
    private String start;
    private String end;
    @Builder.Default
    private int ticketPrice = 30;
}
