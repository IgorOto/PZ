package com.igi.kinoteka_api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.igi.kinoteka_api.model.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie extends BaseEntity{

    @NotEmpty
    @JsonView(value = {View.Open.class, View.Res.class})
    protected String name;
}
