package com.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;      
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          // 1) CORS + disable CSRF
          .cors(cors -> cors.configurationSource(corsConfigurationSource()))
          .csrf(csrf -> csrf.disable())

          // 2) Authorization rules
          .authorizeHttpRequests(auth -> auth
            // static front-end
            .requestMatchers(
              "/", "/*.html", "/**/*.html",
              "/**/*.css", "/**/*.js",
              "/assets/**"
            ).permitAll()

            // public APIs
            .requestMatchers(
              "/api/signup",
              "/api/login",
              "/api/auth/**",
              "/actuator/**"
            ).permitAll()

            // role-restricted
            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/client/**").hasRole("CLIENT")

            // any other API
            .requestMatchers("/api/**").authenticated()

            // everything else
            .anyRequest().permitAll()
          )

          // 3) HTTP Basic (so your JS-stored token is honored)
          .httpBasic();

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(List.of(
            "http://127.0.0.1:5500",
            "http://localhost:5500",
            "http://localhost:8081"
        ));
        cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        cfg.setAllowedHeaders(List.of("*"));
        cfg.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}
