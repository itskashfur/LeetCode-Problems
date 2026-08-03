class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] stores the max relative score advantage starting from index i
        int[] dp = new int[n + 1];

        // Process from right to left (bottom-up dynamic programming)
        for (int i = n - 1; i >= 0; i--) {
            int maxAdvantage = Integer.MIN_VALUE;
            int currentTakeSum = 0;

            // Try taking 1, 2, or 3 stones
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                currentTakeSum += stoneValue[i + k - 1];
                int netGain = currentTakeSum - dp[i + k];
                maxAdvantage = Math.max(maxAdvantage, netGain);
            }

            dp[i] = maxAdvantage;
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}