class Solution {
    public int uniquePaths(int m, int n) {
        // Use a 1D array to save space - O(n) space complexity
        int[] dp = new int[n];
        
        // Initialize the first row: there's only 1 way to reach each cell
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        
        // Fill the DP table row by row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // Number of paths = paths from above + paths from left
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
}