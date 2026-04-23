import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentList, int[] nums) {
        // Base case: if current list size matches nums size, we found a permutation
        if (currentList.size() == nums.length) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if we already used this number in the current path
            if (currentList.contains(nums[i])) {
                continue;
            }

            // 1. Choose: add the number
            currentList.add(nums[i]);

            // 2. Explore: recurse to build the rest of the permutation
            backtrack(result, currentList, nums);

            // 3. Un-choose: remove the last element to backtrack
            currentList.remove(currentList.size() - 1);
        }
    }
}