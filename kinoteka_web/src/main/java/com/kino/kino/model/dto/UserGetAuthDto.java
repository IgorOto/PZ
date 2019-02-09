package com.kino.kino.model.dto;

import com.kino.kino.model.BaseEntity;
import com.kino.kino.model.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserGetAuthDto extends BaseEntity{
    String username;
    String lang;
    Role role;
}
