class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int first = cost[0];
        int second = cost[1];
        
        if (n <= 2) return Math.min(first, second);
        
        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(first, second);
            first = second;
            second = current;
        }
        
        // The final answer is the minimum of the last two steps 
        // as we can reach the top from either of them.
        return Math.min(first, second);
    }
}