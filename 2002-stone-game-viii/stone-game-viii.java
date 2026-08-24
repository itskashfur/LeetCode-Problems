class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Compute prefix sums
        long[] pref = new long[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }

        // Base case: picking all n stones
        long maxDiff = pref[n - 1];

        // Process choices backwards from n - 2 down to 1
        for (int i = n - 2; i >= 1; i--) {
            // maxDiff represents dp[i+1].
            // We take the max between skipping index i (maxDiff) and taking index i (pref[i] - maxDiff).
            maxDiff = Math.max(maxDiff, pref[i] - maxDiff);
        }

        return (int) maxDiff;
    }
}