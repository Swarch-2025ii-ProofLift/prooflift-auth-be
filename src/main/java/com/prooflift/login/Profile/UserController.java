package com.prooflift.login.Profile;


// Clase para manejar las solicitudes relacionadas con los usuarios )

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prooflift.login.User.User;
import com.prooflift.login.User.UserDTO;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService; // inyecta el servicio de usuario


    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID uuid){
        return ResponseEntity.ok(userService.getUserByUuid(uuid));

    }
    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID uuid){
        return ResponseEntity.ok(userService.deleteUserByUuid(uuid));

    }

    @PatchMapping("update/{uuid}")
    public ResponseEntity<User> patchUser(@PathVariable UUID uuid, @RequestBody User userbody) {
        return ResponseEntity.ok(userService.patch_User(uuid, userbody));

    }

    @PatchMapping("change_password/{uuid}")
    public ResponseEntity<String> changePassword(@PathVariable UUID uuid, @RequestBody ChangePasswordRequest passwordRequest) {
        return ResponseEntity.ok(userService.changePassword(uuid, passwordRequest));
    }

}
