package com.igi.kinoteka_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrincipalDto {
    private long id;
    private String username;
    private String roleName;
}
