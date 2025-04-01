import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        
        System.out.println("\nMultiplication Table of " + n + " using for loop:");
        printTableUsingForLoop(n);
        
        System.out.println("\nMultiplication Table of " + n + " using while loop:");
        printTableUsingWhileLoop(n);
        
        System.out.println("\nMultiplication Table of " + n + " using do-while loop:");
        printTableUsingDoWhileLoop(n);
        
        scanner.close();
    }
    
    // For loop implementation
    public static void printTableUsingForLoop(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d × %d = %d\n", n, i, n * i);
        }
    }
    
    // While loop implementation
    public static void printTableUsingWhileLoop(int n) {
        int i = 1;
        while (i <= 10) {
            System.out.printf("%d × %d = %d\n", n, i, n * i);
            i++;
        }
    }
    
    // Do-while loop implementation
    public static void printTableUsingDoWhileLoop(int n) {
        int i = 1;
        do {
            System.out.printf("%d × %d = %d\n", n, i, n * i);
            i++;
        } while (i <= 10);
    }
}
