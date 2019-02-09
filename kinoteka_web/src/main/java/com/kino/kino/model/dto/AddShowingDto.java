package com.kino.kino.model.dto;

import com.kino.kino.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddShowingDto extends BaseEntity {
    private static final DateTimeFormatter formatter = DateTimeFormatter
                                                            .ofPattern("dd/MM/yyyy_HH:mm");
    String datetime;
    long movieId;
    long roomId;

    public LocalDateTime parseDateTime(){
        return LocalDateTime.parse(datetime, formatter);
    }
}
