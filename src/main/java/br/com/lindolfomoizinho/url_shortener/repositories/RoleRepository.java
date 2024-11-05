package br.com.lindolfomoizinho.url_shortener.repositories;

import br.com.lindolfomoizinho.url_shortener.entities.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
