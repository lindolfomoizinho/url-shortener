package br.com.lindolfomoizinho.url_shortener.entities.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
@Entity
@Table(name = "tb_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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
