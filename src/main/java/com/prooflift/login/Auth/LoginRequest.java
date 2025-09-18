package com.prooflift.login.Auth;
// clase para manejar los datos del login
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequest {

    String email;
    String password;

    public String getEmail() {
        return email;
    }

}
