package br.com.lindolfomoizinho.url_shortener.exceptions;

public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException(String username) {
        super("Username already in use: " + username);
    }
}