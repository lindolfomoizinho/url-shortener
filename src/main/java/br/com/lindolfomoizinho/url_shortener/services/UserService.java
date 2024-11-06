package br.com.lindolfomoizinho.url_shortener.services;


import br.com.lindolfomoizinho.url_shortener.entities.user.User;
import br.com.lindolfomoizinho.url_shortener.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void save(User user) {
        userRepository.save(user);
    }
    public Optional<User> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }
}