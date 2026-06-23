class Solution {
    private static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int[] dp = new int[m];
        Arrays.fill(dp, 1);

        for (int i = 2; i <= n; i++) {
            int sum = 0;
            if ((i & 1) == 0)
                for (int j = 0; j < m; j++) {
                    int t = dp[j];
                    dp[j] = sum;
                    sum = (sum + t) % MOD;
                }
            else
                for (int j = m - 1; j >= 0; j--) {
                    int t = dp[j];
                    dp[j] = sum;
                    sum = (sum + t) % MOD;
                }
        }

        int res = 0;
        for (int j = 0; j < m; j++)
            res = (res + dp[j]) % MOD;

        return (res << 1) % MOD;
    }
}