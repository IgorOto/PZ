package com.igi.kinoteka_api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.model.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(value = {View.Open.class, View.Tic.class, View.Res.class})
    protected long id;
}
