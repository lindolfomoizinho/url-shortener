package br.com.lindolfomoizinho.url_shortener.controllers.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) { }