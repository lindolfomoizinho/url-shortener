package br.com.lindolfomoizinho.url_shortener.exceptions;

import br.com.lindolfomoizinho.url_shortener.entities.user.Role;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Role.Values roleValues) {
        super("Role not found: " + roleValues);
    }
}