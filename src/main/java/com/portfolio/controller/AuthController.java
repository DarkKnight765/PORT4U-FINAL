package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.User;
import com.portfolio.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // or your exact frontend origin(s)
public class AuthController {

    @Autowired
    private UserService userService;

    // DTO for both signup & login
    public static class AuthDTO {
        public String username;
        public String email;     // only used on signup
        public String password;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthDTO dto) {
        if (dto.username == null || dto.password == null) {
            return ResponseEntity
                   .badRequest()
                   .body("Username & password required");
        }

        User u = new User();
        u.setUsername(dto.username.trim());
        u.setPassword(dto.password);
        u.setEmail(dto.email == null ? "" : dto.email.trim());

        try {
            userService.registerUser(u);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception ex) {
            if (ex.getMessage().toLowerCase().contains("exists")) {
                return ResponseEntity
                       .status(HttpStatus.CONFLICT)
                       .body(ex.getMessage());
            }
            return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("Server error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO dto) {
        if (dto.username == null || dto.password == null) {
            return ResponseEntity
                   .badRequest()
                   .body("Username & password required");
        }

        boolean valid = userService.validateLogin(
            dto.username.trim(),
            dto.password
        );

        if (valid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity
                   .status(HttpStatus.UNAUTHORIZED)
                   .body("Invalid credentials");
        }
    }
}
