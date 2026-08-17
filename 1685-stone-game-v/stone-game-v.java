class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        
        // Compute prefix sums for O(1) range sum queries
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[i][j] stores the max score achievable from subarray stoneValue[i...j]
        int[][] dp = new int[n][n];

        // Process intervals by increasing length
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // Try all possible split points k (i <= k < j)
                for (int k = i; k < j; k++) {
                    int leftSum = prefix[k + 1] - prefix[i];
                    int rightSum = prefix[j + 1] - prefix[k + 1];

                    if (leftSum < rightSum) {
                        dp[i][j] = Math.max(dp[i][j], leftSum + dp[i][k]);
                    } else if (leftSum > rightSum) {
                        dp[i][j] = Math.max(dp[i][j], rightSum + dp[k + 1][j]);
                    } else { // leftSum == rightSum
                        dp[i][j] = Math.max(dp[i][j], leftSum + Math.max(dp[i][k], dp[k + 1][j]));
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}