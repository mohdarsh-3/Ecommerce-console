package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String username;
    public String password;
    public List<Product> cart = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
