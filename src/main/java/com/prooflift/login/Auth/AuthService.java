package com.prooflift.login.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prooflift.login.Jwt.JwtService;
import com.prooflift.login.User.Role;
import com.prooflift.login.User.User;
import com.prooflift.login.User.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // LOGIN
    public AuthResponse login(LoginRequest request) {
        // autentica al usuario
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        // si la autenticación es correcta, se genera el token
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow();

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }

    // REGISTRO
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .nombre(request.getNombre())
            .apellido(request.getApellido())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword())) // importante: encriptar
            // .edad(request.getEdad())
            // .sexo(request.getSexo())
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }

    public String getUserNameByUuid(UUID uuid) {
        User user = userRepository.findById(uuid)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getNombre();
    }

}
