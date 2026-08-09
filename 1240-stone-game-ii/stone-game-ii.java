class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // Compute suffix sums
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Memoization table: dp[i][M]
        // i ranges from 0 to n, M ranges from 1 to n
        int[][] memo = new int[n][n + 1];

        return helper(0, 1, piles, suffixSum, memo);
    }

    private int helper(int i, int M, int[] piles, int[] suffixSum, int[][] memo) {
        int n = piles.length;
        
        // Base case: if remaining piles can all be taken, take all of them
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return memoized result if already computed
        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;

        // Try picking X piles (1 <= X <= 2 * M)
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            int opponentStones = helper(i + X, nextM, piles, suffixSum, memo);
            int currentStones = suffixSum[i] - opponentStones;
            maxStones = Math.max(maxStones, currentStones);
        }

        memo[i][M] = maxStones;
        return maxStones;
    }
}