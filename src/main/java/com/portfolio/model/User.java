package com.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users",
       uniqueConstraints = {
         @UniqueConstraint(columnNames = "username"),
         @UniqueConstraint(columnNames = "email")
       })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    public User() { }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email    = email;
    }

    // getters & setters...
    public Long   getId()       { return id; }
    public String getUsername() { return username; }
    public void   setUsername(String u) { this.username = u; }
    public String getPassword() { return password; }
    public void   setPassword(String p) { this.password = p; }
    public String getEmail()    { return email; }
    public void   setEmail(String e)    { this.email = e; }
}
