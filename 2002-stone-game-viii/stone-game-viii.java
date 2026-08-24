class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        long[] pref = new long[n];
        pref[0] = stones[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + stones[i];
        }

        long maxDiff = pref[n - 1];

        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, pref[i] - maxDiff);
        }

        return (int) maxDiff;
    }
}