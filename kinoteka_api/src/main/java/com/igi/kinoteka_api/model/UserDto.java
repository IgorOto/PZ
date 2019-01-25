package com.igi.kinoteka_api.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String username;
    private String password;
}
