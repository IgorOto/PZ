package com.igi.repository.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie {
    private long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
