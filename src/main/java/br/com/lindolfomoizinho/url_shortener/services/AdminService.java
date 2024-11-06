package br.com.lindolfomoizinho.url_shortener.services;

import br.com.lindolfomoizinho.url_shortener.controllers.user.dtos.SignupRequest;
import br.com.lindolfomoizinho.url_shortener.entities.user.Role;
import br.com.lindolfomoizinho.url_shortener.exceptions.UsernameAlreadyInUseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminService {
    private final SignupService signupService;
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public AdminService(SignupService signupService) {
        this.signupService = signupService;
    }

    public void createAdminIfNotExist(String username, String password) {
        try {
            signupService.signup(
                    new SignupRequest(username, password),
                    Set.of(Role.Values.ADMIN, Role.Values.BASIC)
            );
            logger.info("Admin user created successfully.");
        } catch (UsernameAlreadyInUseException e) {
            logger.info("Admin already exists.");
        } catch (Exception e) {
            logger.error("Error creating admin user", e);
        }
    }
}