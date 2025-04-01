public class TypePromotion {
    public static void main(String[] args) {
        byte b = 10;
        short s = 20;
        int i = 30;
        long l = 40L;
        float f = 50.0f;
        double d = 60.0;
        char c = 'A'; // 65 in ASCII
        
        // Demonstration of type promotion
        // byte + short -> int
        System.out.println("Type of (b + s): int, Value: " + (b + s));
        
        // int + char -> int
        System.out.println("Type of (i + c): int, Value: " + (i + c));
        
        // long + float -> float
        System.out.println("Type of (l + f): float, Value: " + (l + f));
        
        // float + double -> double
        System.out.println("Type of (f + d): double, Value: " + (f + d));
        
        // Multiple mixed expression
        System.out.println("Final expression (b + s + i + l + f + d + c): double, Value: " 
                          + (b + s + i + l + f + d + c));
    }
}
