package br.com.lindolfomoizinho.url_shortener.entities.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "tb_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Getter
    public enum Values {
        ADMIN(1L),
        BASIC(2L);

        private final long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }
    }
}
