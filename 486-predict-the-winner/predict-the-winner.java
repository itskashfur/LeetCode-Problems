class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // dp[i][j] stores max score difference Player 1 can get from nums[i...j]
        int[][] dp = new int[n][n];

        // Base case: Subarray of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Fill DP table for lengths 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        // Player 1 wins if total net difference is >= 0
        return dp[0][n - 1] >= 0;
    }
}