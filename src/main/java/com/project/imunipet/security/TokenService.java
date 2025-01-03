package com.project.imunipet.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.project.imunipet.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

    private static final String ISSUER = "imunipet";
    private static final int HOURS_EXPIRE = 2;

    @Value("{api.token.secret}")
    private String secret;

    public String generateToken(UserDetailsImpl user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException("Error generating token!", jwtCreationException);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException jwtVerificationException) {
            return "";
        }
    }

    private Instant generateExpirationTime() {
        return LocalDateTime.now().plusHours(HOURS_EXPIRE).toInstant(ZoneOffset.of("-03:00"));
    }
}
