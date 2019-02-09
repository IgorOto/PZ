package com.kino.kino.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie extends BaseEntity{

    String title;
    String description;
    String link;
    String poster;
    String trailer;
    int minutes;
    float price;
    String lang;
}
