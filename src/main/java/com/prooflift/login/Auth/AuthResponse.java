package com.prooflift.login.Auth;
// clase para manejar la respuesta de autenticación (registro y login) delvuelve el token

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// esta clase recibe el token generado en AuthService y lo envia al frontend
public class AuthResponse {
    String token;
}
