package br.com.lindolfomoizinho.url_shortener.controllers.user.dtos;

public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}