public class MajorityElement {
    public int majorityElement(int[] nums) {
        int count = 0, candidate = 0;
        
        // Step 1: Find the candidate for majority element
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        
        // Step 2: Verify the candidate (optional if guaranteed to exist)
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        if (count > nums.length / 2) {
            return candidate;
        }
        return -1; // If no majority element exists
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        MajorityElement me = new MajorityElement();
        System.out.println("Majority Element: " + me.majorityElement(nums));
    }
}
