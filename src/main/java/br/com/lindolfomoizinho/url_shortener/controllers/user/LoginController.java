package br.com.lindolfomoizinho.url_shortener.controllers.user;

import br.com.lindolfomoizinho.url_shortener.controllers.user.dtos.LoginRequest;
import br.com.lindolfomoizinho.url_shortener.controllers.user.dtos.LoginResponse;
import br.com.lindolfomoizinho.url_shortener.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var loginResponse = loginService.login(request);
        return ResponseEntity.ok(loginResponse);
    }
}