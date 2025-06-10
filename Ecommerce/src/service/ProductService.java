package service;

import model.Product;
import model.User;
import util.FileUtil;

import java.util.*;

public class ProductService {
    private List<Product> products = new ArrayList<>();

    public void viewProducts(Scanner sc, User user) {
        System.out.println("\n--- Product List ---");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.print("Enter product ID to add to cart (0 to go back): ");
        int id = Integer.parseInt(sc.nextLine());
        if (id == 0) return;
        for (Product p : products) {
            if (p.id == id) {
                user.cart.add(p);
                System.out.println(p.name + " added to cart.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public void viewCart(User user) {
        if (user.cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\n--- Your Cart ---");
        double total = 0;
        for (Product p : user.cart) {
            System.out.println(p);
            total += p.price;
        }
        System.out.println("Total: ₹" + total);
    }

    public void checkout(User user, Scanner sc) {
        if (user.cart.isEmpty()) {
            System.out.println("Cart is empty. Add products first.");
            return;
        }
        viewCart(user);
        System.out.print("Confirm checkout? (y/n): ");
        char confirm = sc.nextLine().charAt(0);
        if (confirm == 'y' || confirm == 'Y') {
            System.out.println("Order placed successfully!");
            user.cart.clear();
        } else {
            System.out.println("Checkout cancelled.");
        }
    }

    public void loadProducts() {
        List<String> lines = FileUtil.readFile("products.txt");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                products.add(new Product(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2])));
            }
        }
        if (products.isEmpty()) {
            products.add(new Product(1, "T-Shirt", 499));
            products.add(new Product(2, "Shoes", 1299));
            products.add(new Product(3, "Watch", 899));
            products.add(new Product(4, "Bag", 699));
        }
    }

    public void saveProducts() {
        List<String> lines = new ArrayList<>();
        for (Product p : products) {
            lines.add(p.id + "," + p.name + "," + p.price);
        }
        FileUtil.writeFile("products.txt", lines);
    }
}
