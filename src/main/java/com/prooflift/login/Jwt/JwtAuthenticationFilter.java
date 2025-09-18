package com.prooflift.login.Jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // Este filtro se ejecuta una sola vez por cada request
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request); // obtenemos el token del request

        if (token == null) {
            // Si no hay token, dejamos continuar la request sin modificar nada
            filterChain.doFilter(request, response);
            return;
        }

        // Aquí podrías validar el token y establecer la autenticación en el contexto
        // Por ahora solo dejamos continuar
        filterChain.doFilter(request, response);
    }

    // Método auxiliar para extraer el token del encabezado Authorization
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // retornamos el token sin el "Bearer "
        }
        return null;
    }
}
