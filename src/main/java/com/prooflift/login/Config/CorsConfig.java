package com.prooflift.login.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                            "http://localhost:3000",      // Frontend en Docker
                            "http://localhost:5173",      // Frontend local (Vite)
                            "http://frontend:80",         // Comunicación entre contenedores
                            "http://127.0.0.1:3000",     // Alternativa localhost
                            "http://127.0.0.1:5173"      // Alternativa localhost
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true); // Importante para cookies/auth
            }
        };
    }
}