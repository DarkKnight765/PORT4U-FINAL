package com.portfolio.util;

import com.portfolio.model.User;
import java.io.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

public class FileStorageUtil {
    private static final String FILE_PATH = "src/main/resources/users.json";
    private static final Gson gson = new Gson();

    public static List<User> readUsers() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void writeUsers(List<User> users) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
