import java.util.HashMap;
import java.util.Map;

// Custom exceptions for E-Commerce system
class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class PaymentFailedException extends Exception {
    public PaymentFailedException(String message) {
        super(message);
    }
}

class ShippingException extends Exception {
    public ShippingException(String message) {
        super(message);
    }
}

class Product {
    private String id;
    private String name;
    private double price;
    private int stockQuantity;
    
    public Product(String id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    
    // Reduce stock when ordered
    public void reduceStock(int quantity) {
        this.stockQuantity -= quantity;
    }
}

class Order {
    private String orderId;
    private String productId;
    private int quantity;
    private boolean paid;
    private boolean shipped;
    
    public Order(String orderId, String productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.paid = false;
        this.shipped = false;
    }
    
    // Getters
    public String getOrderId() { return orderId; }
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public boolean isPaid() { return paid; }
    public boolean isShipped() { return shipped; }
    
    // Setters
    public void setPaid(boolean paid) { this.paid = paid; }
    public void setShipped(boolean shipped) { this.shipped = shipped; }
}

class ECommerceSystem {
    private Map<String, Product> inventory;
    private Map<String, Order> orders;
    
    public ECommerceSystem() {
        this.inventory = new HashMap<>();
        this.orders = new HashMap<>();
        
        // Initialize with some products
        inventory.put("P001", new Product("P001", "Smartphone", 699.99, 10));
        inventory.put("P002", new Product("P002", "Laptop", 1299.99, 5));
        inventory.put("P003", new Product("P003", "Headphones", 149.99, 20));
    }
    
    // Check if product exists and has sufficient stock
    public void checkAvailability(String productId, int quantity) throws ProductNotFoundException {
        Product product = inventory.get(productId);
        
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
        
        if (product.getStockQuantity() < quantity) {
            throw new ProductNotFoundException("Insufficient stock: requested " + quantity + 
                " but only " + product.getStockQuantity() + " available");
        }
    }
    
    // Process payment (simulated)
    public void processPayment(Order order) throws PaymentFailedException {
        // Simulate payment processing
        double amount = inventory.get(order.getProductId()).getPrice() * order.getQuantity();
        
        // Random failure for demonstration (10% chance of failure)
        if (Math.random() < 0.1) {
            throw new PaymentFailedException("Payment processing failed for order " + order.getOrderId());
        }
        
        order.setPaid(true);
        System.out.println("Payment of $" + amount + " processed successfully for order " + order.getOrderId());
    }
    
    // Process shipping (simulated)
    public void processShipping(Order order) throws ShippingException {
        // Random failure for demonstration (5% chance of failure)
        if (Math.random() < 0.05) {
            throw new ShippingException("Shipping failed for order " + order.getOrderId());
        }
        
        order.setShipped(true);
        System.out.println("Order " + order.getOrderId() + " shipped successfully");
    }
    
    // Create a new order
    public Order createOrder(String productId, int quantity) throws ProductNotFoundException {
        checkAvailability(productId, quantity);
        
        String orderId = "ORD" + System.currentTimeMillis();
        Order order = new Order(orderId, productId, quantity);
        orders.put(orderId, order);
        
        // Reduce stock
        inventory.get(productId).reduceStock(quantity);
        
        System.out.println("Order " + orderId + " created successfully");
        return order;
    }
    
    // Process an order from start to finish
    public void processOrder(String productId, int quantity) {
        try {
            // Create the order
            Order order = createOrder(productId, quantity);
            
            // Process payment
            processPayment(order);
            
            // Process shipping
            processShipping(order);
            
            System.out.println("Order processed successfully!");
            
        } catch (ProductNotFoundException | PaymentFailedException | ShippingException e) {
            // Multi-catch block for all order processing exceptions
            System.out.println("Order processing failed: " + e.getMessage());
            
            // Additional specific handling based on exception type
            if (e instanceof ProductNotFoundException) {
                System.out.println("Please check product ID and quantity");
            } else if (e instanceof PaymentFailedException) {
                System.out.println("Please try a different payment method");
            } else if (e instanceof ShippingException) {
                System.out.println("Please contact customer support for shipping assistance");
            }
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Order processing attempt completed");
        }
    }
}

class ECommerceDemo {
    public static void main(String[] args) {
        ECommerceSystem ecommerce = new ECommerceSystem();
        
        // Process valid order
        System.out.println("--- Processing order for Product P001 ---");
        ecommerce.processOrder("P001", 2);
        
        // Process order with non-existent product
        System.out.println("\n--- Processing order for non-existent product ---");
        ecommerce.processOrder("P999", 1);
        
        // Process order with insufficient quantity
        System.out.println("\n--- Processing order with excessive quantity ---");
        ecommerce.processOrder("P002", 10);
    }
}
