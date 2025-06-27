package com.portfolio.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.portfolio.model.User;

@Service
public class UserService {
    private static final String USERS_FILE = "src/main/resources/users.json";
    private final Gson gson = new Gson();

    private List<User> loadUsers() throws Exception {
        if (!Files.exists(Paths.get(USERS_FILE))) {
            return new ArrayList<>();
        }
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Type listType = new TypeToken<List<User>>() {}.getType();
            List<User> users = gson.fromJson(reader, listType);
            return users == null ? new ArrayList<>() : users;
        }
    }

    private void saveUsers(List<User> users) throws Exception {
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        }
    }

    public void registerUser(User newUser) throws Exception {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(newUser.getUsername())) {
                throw new Exception("Username already exists");
            }
        }
        users.add(newUser);
        saveUsers(users);
    }

    public boolean validateLogin(String username, String password) {
        try {
            for (User u : loadUsers()) {
                if (u.getUsername().equalsIgnoreCase(username)
                    && u.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (Exception ignored) { }
        return false;
    }

    /** Find and return the User (or null) */
    public User findByUsername(String username) {
        try {
            return loadUsers().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean existsByUsername(String username) {
        try {
            for (User u : loadUsers()) {
                if (u.getUsername().equalsIgnoreCase(username)) {
                    return true;
                }
            }
        } catch (Exception ignored) { }
        return false;
    }

    public String findEmailByUsername(String username) throws Exception {
        for (User u : loadUsers()) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u.getEmail();
            }
        }
        return null;
    }
     public List<User> getAllUsers() {
        try {
            return loadUsers();
        } catch (Exception e) {
            // (Optional) log the exception here
            return new ArrayList<>();
        }
    }
}
