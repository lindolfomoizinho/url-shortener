package br.com.lindolfomoizinho.url_shortener.services;

import br.com.lindolfomoizinho.url_shortener.controllers.url.dtos.UrlResponse;
import br.com.lindolfomoizinho.url_shortener.entities.url.Url;
import br.com.lindolfomoizinho.url_shortener.exceptions.ResourceNotFoundException;
import br.com.lindolfomoizinho.url_shortener.exceptions.UnauthorizedAccessException;
import br.com.lindolfomoizinho.url_shortener.exceptions.UrlAlreadyExistsException;
import br.com.lindolfomoizinho.url_shortener.repositories.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
public class UrlService {
    private final UserService userService;
    private final UrlRepository urlRepository;

    public UrlService(UserService userService, UrlRepository urlRepository) {
        this.userService = userService;
        this.urlRepository = urlRepository;
    }

    public UrlResponse getShortenUrl(String originalUrl, JwtAuthenticationToken token) {
        var userId = UUID.fromString(token.getName());

        return urlRepository.findByUserIdAndOriginalUrl(userId, originalUrl)
                .map(this::buildUrlResponse)
                .orElseGet(() -> createAndSaveNewUrl(originalUrl, userId));
    }

    public Page<UrlResponse> getAllUrls(JwtAuthenticationToken token, Pageable pageable) {
        var userId = UUID.fromString(token.getName());
        var urls = urlRepository.findAllByUserId(userId, pageable);
        return urls.map(this::buildUrlResponse);
    }

    public void deleteUrl(UUID id, JwtAuthenticationToken token) {
        var url = urlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found"));

        var userId = UUID.fromString(token.getName());
        if (!url.getUser().getId().equals(userId)) {
            throw new UnauthorizedAccessException("User is not authorized to delete this URL");
        }
        urlRepository.delete(url);
    }

    private UrlResponse createAndSaveNewUrl(String originalUrl, UUID userId) {
        var shortUrl = buildShortenedUrl();

        if (urlRepository.findByUserIdAndShortenedUrl(userId, shortUrl).isPresent()) {
            throw new UrlAlreadyExistsException("This shortened URL already exists.");
        }

        var newUrl = new Url();
        newUrl.setOriginalUrl(originalUrl);
        newUrl.setShortenedUrl(shortUrl);
        newUrl.setUser(userService.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")));

        var urlSaved = urlRepository.save(newUrl);

        return buildUrlResponse(urlSaved);
    }

    private UrlResponse buildUrlResponse(Url url) {
        return new UrlResponse(
                url.getId(),
                url.getShortenedUrl(),
                url.getOriginalUrl(),
                url.getUser().getId(),
                url.getCreatedAt()
        );
    }

    private String getServerUrl() {
        var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString())
                .replacePath("")
                .build()
                .toUriString();
    }

    private String buildShortenedUrl() {
        return getServerUrl() + "/" + UUID.randomUUID()
                .toString()
                .substring(0, 10)
                .replace("-", "");
    }

    public String findShortenUrl(String urlCode) {
        return urlRepository.findByShortenedUrlContaining(urlCode)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new ResourceNotFoundException("Shortened URL not found"));
    }
}