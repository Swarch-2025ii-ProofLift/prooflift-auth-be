package com.prooflift.login.User;
// clase (esquema) para mostrar datos del usuario básicos
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class UserDTO {

    private String nombre;

    private String password;

    private String email;

}
