package com.prooflift.login.Profile;

// clase que maneja la lógica del perfil de usuario

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prooflift.login.Exceptions.InvalidPasswordException;
import com.prooflift.login.User.User;
import com.prooflift.login.User.UserDTO;
import com.prooflift.login.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service // indica que esta clase es un servicio de Spring
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO getUserByUuid(UUID uuid) { // devuelve un User con datos básicos
        UserDTO user = userRepository.findUserDTOById(uuid)
            .orElseThrow(() -> new RuntimeException(" with UUID: " + uuid));
        return user;
    }

    public Void deleteUserByUuid(UUID uuid){
        userRepository.deleteById(uuid);
        //     .orElseThrow(() -> new RuntimeException("User cannot be deleted with UUID:" + uuid));
        return null;
    }

    public User patch_User(UUID uuid, User userbody){
        User updatingUser = userRepository.findById(uuid)
            .orElseThrow (() -> new RuntimeException());

        String nombre = userbody.getNombre();
  
        if (nombre != null){
            updatingUser.setNombre(nombre);
        }

        String email = userbody.getEmail();
        if (email != null){
            updatingUser.setEmail(email);
        }

        return userRepository.save(updatingUser);
    }

    public String changePassword(UUID uuid, ChangePasswordRequest passwordRequest) {
        User user = userRepository.findById(uuid)
            .orElseThrow(() -> new RuntimeException(" with UUID: " + uuid));
        if (passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new InvalidPasswordException("");
        }
        return "Contraseña actualizada correctamente";
    }
}


