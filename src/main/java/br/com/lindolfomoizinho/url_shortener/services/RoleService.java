package br.com.lindolfomoizinho.url_shortener.services;

import br.com.lindolfomoizinho.url_shortener.entities.user.Role;
import br.com.lindolfomoizinho.url_shortener.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role findRoleByName(String name){
        return repository.findByName(name);
    }

    public Optional<Role> findRoleById(Long roleId) {
        return repository.findById(roleId);
    }
}