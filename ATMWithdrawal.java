import java.util.Scanner;

public class ATMWithdrawal {
    private static double accountBalance = 5000.00;
    private static final double DAILY_LIMIT = 1000.00;
    private static double totalWithdrawnToday = 0.00;
    private static final double MIN_WITHDRAWAL = 20.00;
    private static final double MAX_WITHDRAWAL = 500.00;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the ATM");
        System.out.println("Current Balance: $" + accountBalance);
        
        boolean continueTransaction = true;
        while (continueTransaction) {
            System.out.print("Enter withdrawal amount (or 0 to exit): $");
            double amount = scanner.nextDouble();
            
            if (amount == 0) {
                continueTransaction = false;
                System.out.println("Thank you for using our ATM.");
                continue;
            }
            
            boolean success = withdraw(amount);
            
            if (success) {
                System.out.println("Withdrawal successful. New balance: $" + accountBalance);
                System.out.println("Remaining daily limit: $" + (DAILY_LIMIT - totalWithdrawnToday));
            }
            
            System.out.print("Would you like another transaction? (y/n): ");
            String answer = scanner.next();
            continueTransaction = answer.equalsIgnoreCase("y");
        }
        
        scanner.close();
    }
    
    public static boolean withdraw(double amount) {
        // Validate withdrawal amount
        if (amount < MIN_WITHDRAWAL) {
            System.out.println("Error: Minimum withdrawal amount is $" + MIN_WITHDRAWAL);
            return false;
        }
        
        if (amount > MAX_WITHDRAWAL) {
            System.out.println("Error: Maximum withdrawal amount is $" + MAX_WITHDRAWAL);
            return false;
        }
        
        // Check if amount is in multiples of 20
        if (amount % 20 != 0) {
            System.out.println("Error: Withdrawal amount must be in multiples of $20");
            return false;
        }
        
        // Check daily limit
        if (totalWithdrawnToday + amount > DAILY_LIMIT) {
            System.out.println("Error: Daily withdrawal limit exceeded");
            return false;
        }
        
        // Check account balance
        if (amount > accountBalance) {
            System.out.println("Error: Insufficient funds");
            return false;
        }
        
        // Process withdrawal
        accountBalance -= amount;
        totalWithdrawnToday += amount;
        
        return true;
    }
}
