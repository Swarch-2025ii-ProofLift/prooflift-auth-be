package com.prooflift.login.User;
// clase (esquema) para mostrar datos del usuario básicos
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class UserDTO {

    private String nombre;

    @Column(nullable = false)
    private String password;

    private String email;

}
