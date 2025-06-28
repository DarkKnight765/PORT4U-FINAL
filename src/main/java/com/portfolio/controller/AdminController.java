package com.portfolio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.User;
import com.portfolio.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private UserService userService;

    // 1) List all registered usernames
    @GetMapping("/users")
    public ResponseEntity<List<String>> getAllUsers() {
        List<String> usernames = userService
            .getAllUsers()            
            .stream()
            .map(User::getUsername)
            .collect(Collectors.toList());
        return ResponseEntity.ok(usernames);
    }

    // 2) Return a list of “pending” portfolios (dummy data)
    @GetMapping("/portfolios/pending")
    public ResponseEntity<List<PortfolioDTO>> getPendingPortfolios() {
        var sample = List.of(
          new PortfolioDTO(1L, "Alice’s Portfolio", "alice", System.currentTimeMillis()),
          new PortfolioDTO(2L, "Bob’s Portfolio",   "bob",   System.currentTimeMillis() - 86_400_000)
        );
        return ResponseEntity.ok(sample);
    }

    public static record PortfolioDTO(
      Long id,
      String title,
      String author,
      Long createdAt
    ) {}
}
