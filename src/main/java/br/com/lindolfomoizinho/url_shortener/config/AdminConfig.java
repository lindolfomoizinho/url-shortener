package br.com.lindolfomoizinho.url_shortener.config;

import br.com.lindolfomoizinho.url_shortener.services.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AdminConfig implements CommandLineRunner {
    private final AdminService adminService;

    @Value("${admin.credentials.username}")
    private String username;

    @Value("${admin.credentials.password}")
    private String password;

    public AdminConfig(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    @Transactional
    public void run(String... args) {
        adminService.createAdminIfNotExist(username, password);
    }
}