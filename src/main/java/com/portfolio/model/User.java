package com.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT; 

    public User() { }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email    = email;
        this.role     = Role.CLIENT;
    }

    // getters & setters...
    public Long   getId()       { return id; }
    public String getUsername() { return username; }
    public void   setUsername(String u) { this.username = u; }
    public String getPassword() { return password; }
    public void   setPassword(String p) { this.password = p; }
    public String getEmail()    { return email; }
    public void   setEmail(String e)    { this.email = e; }
    public Role getRole() { return role; }
    public void setRole(Role r) { this.role = r; }
}
