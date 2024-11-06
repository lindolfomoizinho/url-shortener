package br.com.lindolfomoizinho.url_shortener.controllers.url;

import br.com.lindolfomoizinho.url_shortener.services.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
public class RedirectController {
    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("{urlCode}")
    public void redirectShortenedUrl(@PathVariable String urlCode, HttpServletResponse response) throws Exception {
        var originalUrl = urlService.findShortenUrl(urlCode);

        if (originalUrl == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Shortened URL not found");
            return;
        }

        response.sendRedirect(originalUrl);
    }
}