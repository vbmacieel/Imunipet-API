package com.project.imunipet.dto.user;

import com.project.imunipet.entity.Role;

import lombok.Data;

@Data
public class ResponseUserDto {
    
    private String id;
    private String name;
    private String login;
    private String password;
    private Role role;
}
