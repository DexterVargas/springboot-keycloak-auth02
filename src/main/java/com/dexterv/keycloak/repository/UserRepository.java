package com.dexterv.keycloak.repository;

import com.dexterv.keycloak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
