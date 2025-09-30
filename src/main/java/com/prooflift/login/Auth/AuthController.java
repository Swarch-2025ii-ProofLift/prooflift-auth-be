package com.prooflift.login.Auth;

// importaciones para que funcionen las anotaciones (@)
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.prooflift.login.User.User;

import lombok.RequiredArgsConstructor;

import java.util.UUID;



@RestController // marca la clase como un controlador REST devuelve JSON
@RequestMapping("/auth") // mapea las solicitudes HTTP a métodos específicos de la clase
@RequiredArgsConstructor // genera un constructor automaticamente para todos los campos finales

public class AuthController {

    private final AuthService authService; // inyecta el servicio de autenticación

    @PostMapping(value ="login") // método post para login
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) // @RequestBody indica que el parámetro viene desde LoginRequest
    { //ResponseEntity es una clase que representa toda la respuesta HTTP
        return ResponseEntity.ok(authService.login(request)); // devuelve la respuesta con el token
    }

    @PostMapping(value ="register") // método post para registro
    public ResponseEntity <AuthResponse> register(@RequestBody RegisterRequest request) // @RequestBody indica que el parámetro viene desde RegisterRequest{
    {    
        return ResponseEntity.ok(authService.register(request)); // devuelve la respuesta con el token
    }
    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user.getNombre()); 
        // devuelve el nombre del usuario autenticado para usarlo en el frontend.
        // usa @AuthenticationPrincipal para obtener el usuario autenticado desde el token
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<String> getUserName(@PathVariable UUID uuid) {
        try {
            String userName = authService.getUserNameByUuid(uuid);
            return ResponseEntity.ok(userName);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
