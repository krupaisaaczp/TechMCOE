public class PatternPrinting {
    public static void main(String[] args) {
        int n = 5; // Number of rows
        
        // Pattern 1: Pyramid
        System.out.println("Pyramid Pattern:");
        for (int i = 1; i <= n; i++) {
            // Print spaces
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            
            // Print stars
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            
            System.out.println();
        }
        
        // Pattern 2: Number Triangle
        System.out.println("\nNumber Triangle Pattern:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
