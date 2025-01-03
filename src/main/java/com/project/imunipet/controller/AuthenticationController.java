package com.project.imunipet.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.imunipet.dto.user.LoginUserDto;
import com.project.imunipet.dto.user.RegisterUserDto;
import com.project.imunipet.dto.user.ResponseUserDto;
import com.project.imunipet.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> register(@Valid @RequestBody RegisterUserDto dto, UriComponentsBuilder uriBuilder) {
        ResponseUserDto responseUser = service.register(dto);
        URI location = uriBuilder.path("api/auth/{id}").buildAndExpand(responseUser.getId()).toUri();
        return ResponseEntity.created(location).body(responseUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserDto dto) {
        String token = service.login(dto);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long id) {
        ResponseUserDto responseUser = service.findUserById(id);
        return ResponseEntity.ok(responseUser);
    }
}
