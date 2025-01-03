package com.project.imunipet.security;

import java.io.IOException;
import java.util.Optional;

import com.project.imunipet.service.impl.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.imunipet.entity.user.base.User;
import com.project.imunipet.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if (token != null) {
            String login = tokenService.validateToken(token);
            Optional<User> optionalUser = userRepository.findByLogin(login);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                UserDetailsImpl userDetails = new UserDetailsImpl(user);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("authorization");
        if (authHeader == null)
            return null;

        return authHeader.replace("Bearer ", "");
    }
}
