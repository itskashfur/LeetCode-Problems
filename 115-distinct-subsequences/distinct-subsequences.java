class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        // dp[j] holds the number of ways to form t[0...j-1]
        double[] dp = new double[n + 1];
        dp[0] = 1; // Base case: 1 way to form empty string t
        for (int i = 1; i <= m; i++) {
            char charS = s.charAt(i - 1);
            // Traverse backwards to avoid overwriting values needed for current row
            for (int j = n; j >= 1; j--) {
                if (charS == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return (int) dp[n];
    }
}