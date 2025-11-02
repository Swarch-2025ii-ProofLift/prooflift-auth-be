package com.prooflift.login.Auth;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.prooflift.login.Jwt.JwtService;
import com.prooflift.login.Profile.EmailService;
import com.prooflift.login.Profile.ForgotPasswordRequest;
import com.prooflift.login.Profile.NewPasswordRequest;
import com.prooflift.login.User.Role;
import com.prooflift.login.User.User;
import com.prooflift.login.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

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
    // obtener el nombre del usuario por su UUID
    public String getUserNameByUuid(UUID uuid) {
        User user = userRepository.findById(uuid)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getNombre();
    }

    // // Recuperar contraseña

    public String recuperarContraseña(ForgotPasswordRequest emailRequest) {
        User user = userRepository.findByEmail(emailRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found with email: " + emailRequest.getEmail()));

        // Código aleatorio
        SecureRandom random = new SecureRandom();
        int resetCode = 1000 + random.nextInt(9000);
        String codeString = String.valueOf(resetCode);

        // expiration time

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10);

        // guardar el codigo en la base

        user.setResetCode(codeString);
        user.setResetCodeExpiration(expirationTime);
        userRepository.save(user);

        emailService.sendEmail(
            user.getEmail(),
            "Password Reset Code",
            "Your password reset code is: " + codeString + ". It will expire in 10 minutes."
        );
        
        return "Codigo generado exitosamente";
    }

    public String validarCodigo(RequestRecuperationCode codeRequest){
        User user = userRepository.findByResetCode(codeRequest.getCode())
            .orElseThrow(() -> new RuntimeException("Invalid reset code"));
        if (user.getResetCodeExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Reset code has expired");
        }

        return "Code is valid";
    }

    public String nuevaContraseña(NewPasswordRequest newPassword ){
        String password = newPassword.getNewPassword();
        String code = newPassword.getResetCode();
        User user = userRepository.findByResetCode(code)
            .orElseThrow(() -> new RuntimeException("Invalid reset code"));
        if (user.getResetCodeExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Reset code has expired");
        }
        if (password == null){
            throw new RuntimeException("Password cannot be null");
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setResetCode(null);
        user.setResetCodeExpiration(null);
        userRepository.save(user);
        return "Password Changed";
        
    }
}