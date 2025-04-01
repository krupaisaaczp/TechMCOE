public class ReverseBits {
    public static void main(String[] args) {
        int num = 43; // 00000000 00000000 00000000 00101011
        int reversed = reverseBits(num);
        
        System.out.println("Original number: " + num + " (" + Integer.toBinaryString(num) + ")");
        System.out.println("Reversed bits: " + reversed + " (" + Integer.toBinaryString(reversed) + ")");
    }
    
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // Left shift result by 1 to make room for the next bit
            result <<= 1;
            // Add the least significant bit of n to result
            result |= (n & 1);
            // Right shift n by 1 to process the next bit
            n >>= 1;
        }
        return result;
    }
}
