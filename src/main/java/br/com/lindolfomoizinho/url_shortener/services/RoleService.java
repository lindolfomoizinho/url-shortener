package br.com.lindolfomoizinho.url_shortener.services;

import br.com.lindolfomoizinho.url_shortener.entities.user.Role;
import br.com.lindolfomoizinho.url_shortener.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role findRoleByName(String name){
        return roleRepository.findByName(name);
    }
    public Optional<Role> findRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }
}