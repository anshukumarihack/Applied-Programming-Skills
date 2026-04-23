import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
        // Base case: if the combination is the right size, add it to result
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Optimization: only loop while there are enough numbers left to reach size k
        for (int i = start; i <= n - (k - current.size()) + 1; i++) {
            // 1. Choose the number
            current.add(i);
            
            // 2. Explore further
            backtrack(result, current, i + 1, n, k);
            
            // 3. Backtrack (un-choose)
            current.remove(current.size() - 1);
        }
    }
}