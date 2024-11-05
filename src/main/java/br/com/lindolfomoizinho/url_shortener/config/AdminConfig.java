package br.com.lindolfomoizinho.url_shortener.config;

import br.com.lindolfomoizinho.url_shortener.controllers.user.dtos.SignupRequest;
import br.com.lindolfomoizinho.url_shortener.entities.user.Role;
import br.com.lindolfomoizinho.url_shortener.services.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Configuration
public class AdminConfig implements CommandLineRunner {
    private final SignupService signupService;
    private static final Logger logger = LoggerFactory.getLogger(AdminConfig.class);

    public AdminConfig(SignupService signupService) {
        this.signupService = signupService;
    }

    @Override
    @Transactional
    public void run(String... args) {
        try {
            signupService.signup(
                    new SignupRequest( "admin", "123"),
                    Set.of(Role.Values.ADMIN, Role.Values.BASIC)
            );
            logger.info("Admin user created successfully.");
        } catch (RuntimeException e) {
            logger.info("Admin already exists.");
        }
    }
}