package com.project.imunipet.service;

import com.project.imunipet.entity.user.Admin;
import com.project.imunipet.entity.user.Client;
import com.project.imunipet.entity.role.RoleName;
import com.project.imunipet.service.impl.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.imunipet.dto.user.LoginUserDto;
import com.project.imunipet.dto.user.RegisterUserDto;
import com.project.imunipet.dto.user.ResponseUserDto;
import com.project.imunipet.entity.user.base.User;
import com.project.imunipet.exception.ResourceNotFoundException;
import com.project.imunipet.repository.UserRepository;
import com.project.imunipet.security.TokenService;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final ModelMapper modelMapper;

    public AuthenticationService(UserRepository repository, AuthenticationManager authenticationManager,
            TokenService tokenService, ModelMapper modelMapper) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.modelMapper = modelMapper;
    }

    public String login(LoginUserDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return tokenService.generateToken(user);
    }

    public ResponseUserDto register(RegisterUserDto dto) {
        RoleName role;
        try {
            role = RoleName.valueOf(dto.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Role not supported");
        }

        String encryptPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = createUserByRole(role, dto.getName(), dto.getLogin(), encryptPassword);
        User userCreated = repository.save(newUser);
        return modelMapper.map(userCreated, ResponseUserDto.class);
    }

    public ResponseUserDto findUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return modelMapper.map(user, ResponseUserDto.class);
    }

    private User createUserByRole(RoleName role, String name, String login, String password) {
        return switch (role) {
            case USER -> new Client(name, login, password);
            case ADMIN -> new Admin(name, login, password);
        };
    }
}
