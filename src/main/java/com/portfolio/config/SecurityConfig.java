package com.portfolio.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .cors(cors -> cors.configurationSource(corsConfigurationSource()))
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
              // Allow unauthenticated access to signup, login, and OTP endpoints
              .requestMatchers(
                  "/api/signup",
                  "/api/login",
                  "/api/auth/**"       // OTP endpoints
              ).permitAll()
              // Allow actuator health/info without auth
              .requestMatchers("/actuator/**").permitAll()
              // All other /api/** requires authentication
              .requestMatchers("/api/**").authenticated()
              // Allow everything else (HTML pages, static resources, etc.)
              .anyRequest().permitAll()
          );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(List.of(
            "http://127.0.0.1:5500",
            "http://localhost:5500",
            "http://localhost:8081"   // if you serve HTML from Spring Boot
        ));
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("*"));
        cfg.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}
