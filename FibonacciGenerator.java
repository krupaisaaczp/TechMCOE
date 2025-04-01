public class FibonacciGenerator {
    public static void main(String[] args) {
        int n = 10; // Generate first 10 Fibonacci numbers
        generateFibonacci(n);
    }
    
    public static void generateFibonacci(int n) {
        System.out.println("First " + n + " Fibonacci numbers:");
        
        int a = 0, b = 1, count = 0;
        
        do {
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
            count++;
        } while (count < n);
        
        System.out.println();
    }
}
