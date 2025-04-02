public class CountingSort {
    public void sort(int[] arr, int maxValue) {
        // Step 1: Create a count array to store the frequency of each element
        int[] count = new int[maxValue + 1];
        
        // Step 2: Count the frequency of each element
        for (int num : arr) {
            count[num]++;
        }
        
        // Step 3: Modify the count array to store the cumulative sum
        for (int i = 1; i <= maxValue; i++) {
            count[i] += count[i - 1];
        }
        
        // Step 4: Build the output array
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        
        // Step 5: Copy the output array back to the original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        int maxValue = 8; // Maximum value in the array
        CountingSort cs = new CountingSort();
        cs.sort(arr, maxValue);
        
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
