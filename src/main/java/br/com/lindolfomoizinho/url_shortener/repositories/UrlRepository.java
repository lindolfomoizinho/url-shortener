package br.com.lindolfomoizinho.url_shortener.repositories;

import br.com.lindolfomoizinho.url_shortener.entities.url.Url;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<Url, UUID> {
    Optional<Url> findByUserIdAndOriginalUrl(UUID userId, String originalUrl);
    Optional<Url> findByShortenedUrlContaining(String urlCode);
    Optional<Url> findByUserIdAndShortenedUrl(UUID userId, String shortUrl);
    Page<Url> findAllByUserId(UUID userId, Pageable pageable);
}
