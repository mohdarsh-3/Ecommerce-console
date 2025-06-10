package main;

import model.User;
import service.ProductService;
import service.UserService;

import java.util.Scanner;

public class EcommerceApp {
    static Scanner sc = new Scanner(System.in);
    static UserService userService = new UserService();
    static ProductService productService = new ProductService();

    public static void main(String[] args) {
        productService.loadProducts();
        userService.loadUsers();
        mainMenu();
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n--- E-Commerce Terminal App ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> userService.register(sc);
                case "2" -> {
                    User user = userService.login(sc);
                    if (user != null) userMenu(user);
                }
                case "3" -> {
                    userService.saveUsers();
                    productService.saveProducts();
                    System.out.println("Thank you for visiting!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void userMenu(User user) {
        while (true) {
            System.out.println("\n--- Welcome, " + user.username + " ---");
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> productService.viewProducts(sc, user);
                case "2" -> productService.viewCart(user);
                case "3" -> productService.checkout(user, sc);
                case "4" -> {
                    System.out.println("Logged out successfully.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
