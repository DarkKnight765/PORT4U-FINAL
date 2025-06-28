package com.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.Role;
import com.portfolio.model.User;
import com.portfolio.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    // --- DTOs ---
    public static class AuthDTO {
        public String username;
        public String email;    
        public String password;
        public String role;     
    }

    public static record LoginResponse(String username, Role role) { }

    // --- Signup Endpoint ---
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthDTO dto) {
        if (dto.username == null || dto.password == null) {
            return ResponseEntity
                   .badRequest()
                   .body("Username & password required");
        }

        // build user with role (default CLIENT)
        Role chosenRole = Role.CLIENT;
        if ("ADMIN".equalsIgnoreCase(dto.role)) {
            chosenRole = Role.ADMIN;
        }

        User u = new User();
        u.setUsername(dto.username.trim());
        u.setPassword(dto.password);
        u.setEmail(dto.email == null ? "" : dto.email.trim());
        u.setRole(chosenRole);

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

    // --- Login Endpoint ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO dto) {
        if (dto.username == null || dto.password == null) {
            return ResponseEntity
                   .badRequest()
                   .body("Username & password required");
        }

        User u = userService.findByUsername(dto.username.trim());
        if (u != null && userService.validateLogin(dto.username.trim(), dto.password)) {
            // return JSON with role
            return ResponseEntity.ok(new LoginResponse(u.getUsername(), u.getRole()));
        } else {
            return ResponseEntity
                   .status(HttpStatus.UNAUTHORIZED)
                   .body("Invalid credentials");
        }
    }
}
