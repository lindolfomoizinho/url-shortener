package br.com.lindolfomoizinho.url_shortener.controllers.user.dtos;

public record SignupRequest (
        String username,
        String password
) { }