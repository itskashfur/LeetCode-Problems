class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int MOD = 1_000_000_007;

        // Count non-zero digits to allocate exact sizing arrays
        int k = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') k++;
        }

        int[] vals = new int[k];
        int[] pos = new int[k];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                vals[idx] = c - '0';
                pos[idx] = i;
                idx++;
            }
        }

        // Precompute powers of 10 modulo 10^9+7
        long[] pow10 = new long[k + 1];
        pow10[0] = 1;
        for (int i = 1; i <= k; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Precompute prefix sums of digits and prefix concatenation values
        long[] prefSum = new long[k + 1];
        long[] prefX = new long[k + 1];
        for (int i = 0; i < k; i++) {
            prefSum[i + 1] = prefSum[i] + vals[i];
            prefX[i + 1] = (prefX[i] * 10 + vals[i]) % MOD;
        }

        // Precompute next and previous non-zero position pointers
        int[] nextNonZero = new int[m];
        int[] prevNonZero = new int[m];
        
        int currNext = k;
        for (int i = m - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                // Find where this index sits in the 'vals' array
                currNext = Arrays.binarySearch(pos, i);
            }
            nextNonZero[i] = currNext;
        }

        int currPrev = -1;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                currPrev = Arrays.binarySearch(pos, i);
            }
            prevNonZero[i] = currPrev;
        }
        // Process each query in O(1) time
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int idxL = nextNonZero[l];
            int idxR = prevNonZero[r];

            // If no non-zero digit falls inside the query window bounds
            if (idxL > idxR || idxL == k || idxR == -1) {
                ans[i] = 0;
            } else {
                int len = idxR - idxL + 1;
                
                // Extract the concatenated numerical value 'x' via prefix subtraction
                long x = (prefX[idxR + 1] - (prefX[idxL] * pow10[len]) % MOD + MOD) % MOD;
                
                // Fetch the total sum of digits via standard prefix subtraction
                long sum = prefSum[idxR + 1] - prefSum[idxL];

                ans[i] = (int) ((x * (sum % MOD)) % MOD);
            }
        }
        return ans;
    }
}