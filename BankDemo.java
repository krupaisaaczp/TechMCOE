
// Custom checked exception for insufficient funds
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Custom checked exception for invalid account
class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}

// Custom unchecked exception for security violations
class SecurityViolationException extends RuntimeException {
    public SecurityViolationException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;
    private boolean locked;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.locked = false;
    }
    
    // Method that throws checked exception
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAccountException {
        // Check if account is locked
        if (locked) {
            throw new InvalidAccountException("Account is locked");
        }
        
        // Check if amount is valid
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        // Check if sufficient funds
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds: required $" + 
                amount + " but only have $" + balance);
        }
        
        balance -= amount;
        System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
    }
    
    // Method that throws unchecked exception
    public void lockAccount(String password) {
        // Simple security check
        if (!"admin123".equals(password)) {
            throw new SecurityViolationException("Invalid password for locking account");
        }
        
        this.locked = true;
        System.out.println("Account locked successfully");
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public boolean isLocked() {
        return locked;
    }
}

class BankDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("123456789", 1000.0);
        
        // Handling checked exceptions
        try {
            account.withdraw(500);
            System.out.println("First withdrawal successful");
            
            account.withdraw(700);  // This should throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        } catch (InvalidAccountException e) {
            System.out.println("Account error: " + e.getMessage());
        }
        
        // Handling unchecked exceptions
        try {
            account.lockAccount("wrongPassword");  // This should throw SecurityViolationException
        } catch (SecurityViolationException e) {
            System.out.println("Security error: " + e.getMessage());
        }
        
        try {
            account.withdraw(-100);  // This should throw IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Other error: " + e.getMessage());
        }
    }
}
