import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Start the recursive process with an empty subset
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int start) {
        // Add the subset formed so far to the result list
        result.add(new ArrayList<>(currentSubset));

        for (int i = start; i < nums.length; i++) {
            // 1. Include the number in the subset
            currentSubset.add(nums[i]);
            
            // 2. Move to the next element
            backtrack(result, currentSubset, nums, i + 1);
            
            // 3. Backtrack: remove the number to explore other possibilities
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}