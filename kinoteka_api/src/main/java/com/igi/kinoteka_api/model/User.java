package com.igi.kinoteka_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User extends BaseEntity {

    protected String username;

    protected String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_role")
    @JsonIgnore
    protected Role role;
}
