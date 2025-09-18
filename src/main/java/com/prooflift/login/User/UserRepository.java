package com.prooflift.login.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar por email (que se usa como username)
    Optional<User> findByEmail(String email);
}
