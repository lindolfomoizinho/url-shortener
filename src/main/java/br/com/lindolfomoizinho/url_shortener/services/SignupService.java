package br.com.lindolfomoizinho.url_shortener.services;


import br.com.lindolfomoizinho.url_shortener.controllers.user.dtos.SignupRequest;
import br.com.lindolfomoizinho.url_shortener.entities.user.Role;
import br.com.lindolfomoizinho.url_shortener.entities.user.User;
import br.com.lindolfomoizinho.url_shortener.exceptions.RoleNotFoundException;
import br.com.lindolfomoizinho.url_shortener.exceptions.UsernameAlreadyInUseException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SignupService {

    private final RoleService roleService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public SignupService(RoleService roleService, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignupRequest signupRequest, Set<Role.Values> roles) {
        validateUsername(signupRequest.username());
        var userRoles = fetchRolesByIds(roles);

        var user = buildUser(signupRequest, userRoles);
        userService.save(user);
    }

    private void validateUsername(String username) {
        userService.findByUsername(username)
                .ifPresent(user -> {
                    throw new UsernameAlreadyInUseException(username);
                });
    }

    private Set<Role> fetchRolesByIds(Set<Role.Values> roles) {
        return roles.stream()
                .map(roleEnum -> roleService.findRoleById(roleEnum.getRoleId())
                        .orElseThrow(() -> new RoleNotFoundException(roleEnum)))
                .collect(Collectors.toSet());
    }

    private User buildUser(SignupRequest signupRequest, Set<Role> roles) {
        var user = new User();
        user.setUsername(signupRequest.username());
        user.setPassword(passwordEncoder.encode(signupRequest.password()));
        user.setRoles(roles);
        return user;
    }
}