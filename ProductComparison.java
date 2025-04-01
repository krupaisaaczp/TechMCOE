import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int rating;
    
    public Product(String name, double price, int rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
    
    // Getter methods
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getRating() { return rating; }
    
    // Custom comparison based on price
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }
    
    @Override
    public String toString() {
        return name + " - $" + price + " (Rating: " + rating + "/5)";
    }
}

public class ProductComparison {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 899.99, 4));
        products.add(new Product("Smartphone", 499.99, 5));
        products.add(new Product("Headphones", 149.99, 3));
        products.add(new Product("Tablet", 299.99, 4));
        
        System.out.println("Products before sorting:");
        for (Product p : products) {
            System.out.println(p);
        }
        
        // Sort by price (default comparison)
        Collections.sort(products);
        
        System.out.println("\nProducts sorted by price:");
        for (Product p : products) {
            System.out.println(p);
        }
        
        // Sort by rating using a custom comparator
        Collections.sort(products, (p1, p2) -> Integer.compare(p2.getRating(), p1.getRating()));
        
        System.out.println("\nProducts sorted by rating (highest first):");
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
