package com.igi.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {
    private long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
