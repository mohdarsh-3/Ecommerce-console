package model;

public class Product {
    public int id;
    public String name;
    public double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return id + ". " + name + " - ₹" + price;
    }
}
