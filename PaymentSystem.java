class PaymentProcessor {
    // Process payment with credit card
    public boolean processPayment(String cardNumber, String expDate, String cvv, double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        System.out.println("Card Number: " + maskCardNumber(cardNumber));
        System.out.println("Expiration Date: " + expDate);
        // Payment processing logic here
        return true;
    }
    
    // Process payment with PayPal
    public boolean processPayment(String email, String password, double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        System.out.println("PayPal Account: " + email);
        // PayPal payment processing logic here
        return true;
    }
    
    // Process payment with bank transfer
    public boolean processPayment(String accountNumber, String routingNumber, double amount) {
        System.out.println("Processing bank transfer of $" + amount);
        System.out.println("Account Number: " + maskAccountNumber(accountNumber));
        System.out.println("Routing Number: " + routingNumber);
        // Bank transfer processing logic here
        return true;
    }
    
    // Process cash payment
    public boolean processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);
        // Cash payment processing logic here
        return true;
    }
    
    // Helper methods
    private String maskCardNumber(String cardNumber) {
        return "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4);
    }
    
    private String maskAccountNumber(String accountNumber) {
        return "XXXXX" + accountNumber.substring(accountNumber.length() - 4);
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        
        // Test different payment methods using overloaded methods
        System.out.println("=== Credit Card Payment ===");
        processor.processPayment("1234567890123456", "12/25", "123", 99.99);
        
        System.out.println("\n=== PayPal Payment ===");
        processor.processPayment("user@example.com", "password123", 59.99);
        
        System.out.println("\n=== Bank Transfer ===");
        processor.processPayment("12345678901234", "021000021", 500.00);
        
        System.out.println("\n=== Cash Payment ===");
        processor.processPayment(25.50);
    }
}
