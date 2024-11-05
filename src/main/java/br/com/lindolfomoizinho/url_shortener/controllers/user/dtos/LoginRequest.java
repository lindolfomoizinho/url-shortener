package br.com.lindolfomoizinho.url_shortener.controllers.user.dtos;

public record LoginRequest(
        String username,
        String password
) {
}