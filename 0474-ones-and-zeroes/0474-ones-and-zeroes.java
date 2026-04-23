class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] = max strings with at most i zeros and j ones
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int[] count = countZerosOnes(s);
            int zeros = count[0];
            int ones = count[1];

            // Iterate backwards to avoid using the same string multiple times
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }

        return dp[m][n];
    }

    private int[] countZerosOnes(String s) {
        int[] count = new int[2];
        for (char c : s.toCharArray()) {
            count[c - '0']++;
        }
        return count;
    }
}