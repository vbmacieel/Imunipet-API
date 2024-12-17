package com.project.imunipet.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserDto {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
