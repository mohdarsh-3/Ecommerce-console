package service;

import model.User;
import util.FileUtil;

import java.util.*;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public void register(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Registration successful!");
    }

    public User login(Scanner sc) {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            System.out.println("Login successful!");
            return user;
        }
        System.out.println("Invalid credentials.");
        return null;
    }

    public void loadUsers() {
        List<String> lines = FileUtil.readFile("users.txt");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                users.put(parts[0], new User(parts[0], parts[1]));
            }
        }
    }

    public void saveUsers() {
        List<String> lines = new ArrayList<>();
        for (User user : users.values()) {
            lines.add(user.username + "," + user.password);
        }
        FileUtil.writeFile("users.txt", lines);
    }
}
