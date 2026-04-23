class Solution {
    public int findJudge(int n, int[][] trust) {
        // If there is only one person and no trust relationships, they are the judge
        if (trust.length < n - 1) return -1;

        // Array to track the net trust score for each person (1 to n)
        int[] trustScores = new int[n + 1];

        for (int[] relation : trust) {
            int personA = relation[0];
            int personB = relation[1];

            // personA trusts someone, so they cannot be the judge
            trustScores[personA]--;

            // personB is trusted by someone
            trustScores[personB]++;
        }

        // The judge must have a trust score of n - 1
        for (int i = 1; i <= n; i++) {
            if (trustScores[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }
}