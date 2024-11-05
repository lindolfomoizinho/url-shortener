package br.com.lindolfomoizinho.url_shortener.controllers.user;

import br.com.lindolfomoizinho.url_shortener.controllers.user.dtos.SignupRequest;
import br.com.lindolfomoizinho.url_shortener.entities.user.Role;
import br.com.lindolfomoizinho.url_shortener.services.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/v1/user")
public class SignupController {
    private final SignupService service;

    public SignupController(SignupService service) {
        this.service = service;
    }


    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request) {
        service.signup(request, Set.of(Role.Values.BASIC));
        return ResponseEntity.ok().build();
    }
}
