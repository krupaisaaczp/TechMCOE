public class PowerOfTwo {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 4, 8, 16, 15, 32, 33, 64};
        
        for (int num : numbers) {
            System.out.println(num + " is " + (isPowerOfTwo(num) ? "" : "not ") + "a power of two");
        }
    }
    
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
