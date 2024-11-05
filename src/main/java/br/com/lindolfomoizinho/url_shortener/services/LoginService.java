package br.com.lindolfomoizinho.url_shortener.services;


import br.com.lindolfomoizinho.url_shortener.controllers.user.dtos.LoginRequest;
import br.com.lindolfomoizinho.url_shortener.controllers.user.dtos.LoginResponse;
import br.com.lindolfomoizinho.url_shortener.entities.user.Role;
import br.com.lindolfomoizinho.url_shortener.entities.user.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService {
    private final UserService service;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginService(UserService service, BCryptPasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        Optional<User> user = service.findByUsername(request.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(request, passwordEncoder)) {
            throw new BadCredentialsException("User or Password is invalid!");
        }

        Instant now = Instant.now();
        long expiresIn = 300L;

        String scopes = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("task-manager")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);
    }
}