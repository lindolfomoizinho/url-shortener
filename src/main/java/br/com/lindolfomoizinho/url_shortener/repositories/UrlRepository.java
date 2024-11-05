package br.com.lindolfomoizinho.url_shortener.repositories;

import br.com.lindolfomoizinho.url_shortener.entities.url.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<Url, UUID> {
    Optional<Url> findByShortenedUrlEquals(String urlShortened);
}