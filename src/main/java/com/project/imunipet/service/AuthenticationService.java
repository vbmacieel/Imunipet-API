package com.project.imunipet.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.imunipet.dto.user.LoginUserDto;
import com.project.imunipet.dto.user.RegisterUserDto;
import com.project.imunipet.dto.user.ResponseUserDto;
import com.project.imunipet.entity.User;
import com.project.imunipet.exception.ResourceNotFoundException;
import com.project.imunipet.repository.UserRepository;
import com.project.imunipet.security.TokenService;

@Service
public class AuthenticationService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public String login(LoginUserDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getUsername());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return tokenService.generateToken((User) authentication.getPrincipal());
    }

    public ResponseUserDto register(RegisterUserDto dto) {
        String encryptPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User newUser = new User(dto.getName(), dto.getLogin(), encryptPassword, dto.getRole());
        repository.save(newUser);

        return modelMapper.map(newUser, ResponseUserDto.class);
    }

    public ResponseUserDto findUserById(String id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return modelMapper.map(user, ResponseUserDto.class);
    }
}
