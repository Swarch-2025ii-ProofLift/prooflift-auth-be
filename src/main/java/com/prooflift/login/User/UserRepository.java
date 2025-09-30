package com.prooflift.login.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Buscar por email (que se usa como username)
    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);
}