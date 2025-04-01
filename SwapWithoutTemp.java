public class SwapWithoutTemp {
    public static void main(String[] args) {
        // Using arithmetic operations
        int a = 5, b = 10;
        System.out.println("Before swap: a = " + a + ", b = " + b);
        
        a = a + b; // a now contains sum of both
        b = a - b; // b gets original value of a
        a = a - b; // a gets original value of b
        
        System.out.println("After swap: a = " + a + ", b = " + b);
        
        // Alternative using XOR (works only for integers)
        int x = 15, y = 20;
        System.out.println("Before XOR swap: x = " + x + ", y = " + y);
        
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        
        System.out.println("After XOR swap: x = " + x + ", y = " + y);
    }
}
