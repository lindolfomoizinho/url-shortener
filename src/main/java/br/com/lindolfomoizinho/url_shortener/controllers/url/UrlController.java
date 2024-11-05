package br.com.lindolfomoizinho.url_shortener.controllers.url;

import br.com.lindolfomoizinho.url_shortener.controllers.url.dtos.UrlResponse;
import br.com.lindolfomoizinho.url_shortener.services.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/v1/shorten")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> shortenUrl(@RequestParam String url, JwtAuthenticationToken token) {
        return ResponseEntity.ok(urlService.shortenUrl(url, token));


    }
}
