package br.com.lindolfomoizinho.url_shortener.controllers.url;

import br.com.lindolfomoizinho.url_shortener.controllers.url.dtos.UrlResponse;
import br.com.lindolfomoizinho.url_shortener.services.UrlService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/url")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestParam String originalUrl, JwtAuthenticationToken token) {
        return ResponseEntity.ok(urlService.getShortenUrl(originalUrl, token));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UrlResponse>> getAllUrls(JwtAuthenticationToken token, Pageable pageable) {
        return ResponseEntity.ok(urlService.getAllUrls(token, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable UUID id, JwtAuthenticationToken token) {
        urlService.deleteUrl(id, token);
        return ResponseEntity.noContent().build();
    }
}