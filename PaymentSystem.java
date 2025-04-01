// Define an interface for Payment processing
interface PaymentMethod {
    boolean processPayment(double amount);
}

// Credit Card Payment Processor
class CreditCardPayment implements PaymentMethod {
    private String cardNumber, expDate, cvv;

    public CreditCardPayment(String cardNumber, String expDate, String cvv) {
        this.cardNumber = maskCardNumber(cardNumber);
        this.expDate = expDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiration Date: " + expDate);
        return true;
    }

    private String maskCardNumber(String cardNumber) {
        return "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4);
    }
}

// PayPal Payment Processor
class PayPalPayment implements PaymentMethod {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        System.out.println("PayPal Account: " + maskEmail(email));
        return true;
    }

    private String maskEmail(String email) {
        return email.replaceAll("(?<=.{2}).(?=.*@)", "*");
    }
}

// Bank Transfer Processor
class BankTransferPayment implements PaymentMethod {
    private String accountNumber, routingNumber;

    public BankTransferPayment(String accountNumber, String routingNumber) {
        this.accountNumber = maskAccountNumber(accountNumber);
        this.routingNumber = routingNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing bank transfer of $" + amount);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Routing Number: " + routingNumber);
        return true;
    }

    private String maskAccountNumber(String accountNumber) {
        return "XXXXX" + accountNumber.substring(accountNumber.length() - 4);
    }
}

// Cash Payment Processor
class CashPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);
        return true;
    }
}

// Payment System Runner
public class PaymentSystem {
    public static void main(String[] args) {
        PaymentMethod creditCard = new CreditCardPayment("1234567890123456", "12/25", "123");
        PaymentMethod paypal = new PayPalPayment("user@example.com");
        PaymentMethod bankTransfer = new BankTransferPayment("12345678901234", "021000021");
        PaymentMethod cash = new CashPayment();

        System.out.println("=== Credit Card Payment ===");
        creditCard.processPayment(99.99);

        System.out.println("\n=== PayPal Payment ===");
        paypal.processPayment(59.99);

        System.out.println("\n=== Bank Transfer ===");
        bankTransfer.processPayment(500.00);

        System.out.println("\n=== Cash Payment ===");
        cash.processPayment(25.50);
    }
}
