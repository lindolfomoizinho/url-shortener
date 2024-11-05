package br.com.lindolfomoizinho.url_shortener.entities.url;

import br.com.lindolfomoizinho.url_shortener.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String originalUrl;
    @NotBlank
    private String shortenedUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    private Instant createdAt;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(shortenedUrl, url.shortenedUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortenedUrl);
    }
}
