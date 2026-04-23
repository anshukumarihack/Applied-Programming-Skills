class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // Tracks maximum money robbed up to two houses ago
        int prev2 = 0;
        // Tracks maximum money robbed up to the previous house
        int prev1 = 0;

        for (int num : nums) {
            // Option 1: Skip current house (keep prev1)
            // Option 2: Rob current house (num + prev2)
            int current = Math.max(prev1, num + prev2);
            
            // Move pointers forward
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}