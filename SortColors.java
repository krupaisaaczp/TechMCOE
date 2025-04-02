public class SortColors {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap nums[low] and nums[mid], increment both
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // Just move mid forward
                mid++;
            } else { // nums[mid] == 2
                // Swap nums[mid] and nums[high], decrement high
                swap(nums, mid, high);
                high--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors sc = new SortColors();
        sc.sortColors(nums);
        
        System.out.println("Sorted colors:");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
