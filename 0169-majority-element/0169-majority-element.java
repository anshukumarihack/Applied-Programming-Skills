class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            // If count is 0, we pick a new candidate
            if (count == 0) {
                candidate = num;
            }
            
            // If the current number matches the candidate, increment; 
            // otherwise, decrement (voting against)
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}