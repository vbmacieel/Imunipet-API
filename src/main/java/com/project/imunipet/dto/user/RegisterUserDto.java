package com.project.imunipet.dto.user;

import com.project.imunipet.entity.Role;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDto {

    @NotNull
    @Size(min = 5, max = 50)
    private String name;
    @NotNull
    @Size(min = 5, max = 50)
    private String login;
    @NotNull
    private String password;
    @NotNull
    private Role role;
}
