package com.dexterv.keycloak.service;

import com.dexterv.keycloak.entity.User;
import com.dexterv.keycloak.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> findAll() { return repo.findAll(); }
    public User save(User p) { return repo.save(p); }
    public void delete(Long id) { repo.deleteById(id); }
}
