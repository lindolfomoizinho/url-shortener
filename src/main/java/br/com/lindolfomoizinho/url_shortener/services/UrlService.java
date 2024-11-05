package br.com.lindolfomoizinho.url_shortener.services;

import br.com.lindolfomoizinho.url_shortener.controllers.url.dtos.UrlResponse;
import br.com.lindolfomoizinho.url_shortener.entities.user.User;
import br.com.lindolfomoizinho.url_shortener.repositories.UrlRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    private final UserService userService;
    private final UrlRepository urlRepository;

    public UrlService(UserService userService, UrlRepository urlRepository) {
        this.userService = userService;
        this.urlRepository = urlRepository;
    }

    public UrlResponse shortenUrl(String originalUrl, JwtAuthenticationToken token) {
        Optional<User> user = userService.findById(UUID.fromString(token.getName()));
            if(urlRepository.findByUrlOriginalEquals(urlOriginal).isEmpty()){
                String urlShortened = urlShorten(urlOriginal);
                repository.save(UrlSchema.builder()
                        .urlOriginal(urlOriginal)
                        .urlShortened(urlShortened)
                        .expirationDate(new Date())
                        .build());
                return urlShortened;
            }
            else return repository.findByUrlOriginalEquals(urlOriginal)
                    .orElseThrow()
                    .getUrlShortened();
        }
    }


    private String getServerUrl(String urlOriginal){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest()
                .getRequestURL()
                .toString().replace(urlOriginal, "");
    }
    private String buildShortenedUrl(String urlOriginal){
        return getServerUrl(urlOriginal) + UUID.randomUUID()
                .toString()
                .substring(0, 10)
                .replace("-","");
    }

}
