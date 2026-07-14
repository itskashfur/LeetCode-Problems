import java.util.Arrays;

class Solution {
    private int[][][] memo;
    private int[] nums;
    private int n;
    private final int MOD = 1_000_000_007;

    // Helper method to compute GCD
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        
        // Find the maximum value in nums to define memoization boundaries (max <= 200)
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // DP state: dp[index][gcd1][gcd2]
        // g1 and g2 go from 0 to maxVal
        memo = new int[n][maxVal + 1][maxVal + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= maxVal; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return solve(0, 0, 0);
    }

    private int solve(int idx, int g1, int g2) {
        // Base Case: If we have reached the end of the array
        if (idx == n) {
            // Both subsequences must be non-empty and have matching GCDs
            return (g1 > 0 && g1 == g2) ? 1 : 0;
        }

        // Return already calculated state
        if (memo[idx][g1][g2] != -1) {
            return memo[idx][g1][g2];
        }

        // Option 1: Skip the current element
        long res = solve(idx + 1, g1, g2);

        // Option 2: Put nums[idx] into Sequence 1
        int nextG1 = (g1 == 0) ? nums[idx] : gcd(g1, nums[idx]);
        res = (res + solve(idx + 1, nextG1, g2)) % MOD;

        // Option 3: Put nums[idx] into Sequence 2
        int nextG2 = (g2 == 0) ? nums[idx] : gcd(g2, nums[idx]);
        res = (res + solve(idx + 1, g1, nextG2)) % MOD;

        return memo[idx][g1][g2] = (int) res;
    }
}

/* 
==========================================
Example Trace: nums = [1, 2, 3, 4]
------------------------------------------
Output: 10
There are 10 unique disjoint pairs of subsequences that share the same GCD (all of GCD 1):
- seq1: [1], seq2: [2, 3]       (GCDs: 1 and 1)
- seq1: [1], seq2: [2, 3, 4]    (GCDs: 1 and 1)
- seq1: [1], seq2: [3, 4]       (GCDs: 1 and 1)
- seq1: [1, 2], seq2: [3, 4]    (GCDs: 1 and 1)
- seq1: [1, 4], seq2: [2, 3]    (GCDs: 1 and 1)
- ... (and their mirrored pairs where seq1 and seq2 swap elements)
==========================================
*/