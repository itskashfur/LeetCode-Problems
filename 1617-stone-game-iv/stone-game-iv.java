class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                // If removing k*k stones leaves opponent in a losing state,
                // the current player wins from state i.
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // No need to check other square numbers
                }
            }
        }
        
        return dp[n];
    }
}