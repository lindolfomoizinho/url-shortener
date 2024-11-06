package br.com.lindolfomoizinho.url_shortener.controllers.url.dtos;

import java.time.Instant;
import java.util.UUID;

public record UrlResponse(
        UUID id,
        String shortenedUrl,
        String originalUrl,
        UUID userId,
        Instant createdAt
) { }