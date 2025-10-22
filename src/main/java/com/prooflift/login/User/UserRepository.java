package com.prooflift.login.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// no es clase, es una interfaz que se comunica con la base de datos.
public interface UserRepository extends JpaRepository<User, UUID> {

    // Buscar por email (que se usa como username)
    Optional<User> findByEmail(String email);
    // Busca por el id único (UUID)
    Optional<User> findById(UUID id);
    Optional<UserDTO> findUserDTOById(UUID id);
    Optional<User> findByResetCode(String resetCode);
}

