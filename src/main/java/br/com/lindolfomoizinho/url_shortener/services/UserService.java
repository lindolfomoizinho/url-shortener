package br.com.lindolfomoizinho.url_shortener.services;


import br.com.lindolfomoizinho.url_shortener.entities.user.User;
import br.com.lindolfomoizinho.url_shortener.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void save(User user) {
        repository.save(user);
    }

    public Optional<User> findById(UUID uuid) {
        return repository.findById(uuid);
    }
}