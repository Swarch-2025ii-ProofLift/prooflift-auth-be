package com.prooflift.login.Auth;
// clase para manejar los datos del registro


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// esta clase recibe los datos que envia el frontend y los mapea y usa en AuthController y AuthService
// es como el schema
public class RegisterRequest {

    String email;
    String password;
    String nombre;
    String apellido;
    // Integer edad;
    // String sexo;
    // String ciudad;

    public String getEmail() {
        return email;
    }

}
