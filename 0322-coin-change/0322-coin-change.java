import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i] will store the minimum coins needed for amount i
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        
        // Fill the array with a value representing infinity
        Arrays.fill(dp, max);
        
        // Base case: 0 coins are needed to make amount 0
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    // Min(current value, 1 + value for remaining amount)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] wasn't updated, it's impossible to reach
        return dp[amount] > amount ? -1 : dp[amount];
    }
}