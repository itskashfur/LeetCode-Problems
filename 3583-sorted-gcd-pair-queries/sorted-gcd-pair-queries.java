import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Find the maximum value in the array to set our processing bounds
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // Count how many times each number appears in the input array
        int[] counts = new int[maxVal + 1];
        for (int x : nums) {
            counts[x]++;
        }

        // Compute exact counts of pairs having an absolute GCD of 'i' using backward inclusion-exclusion
        long[] exactGcdPairs = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long multiplesCount = 0;
            
            // Gather the total frequency of all elements that are divisible by 'i'
            for (int j = i; j <= maxVal; j += i) {
                multiplesCount += counts[j];
            }
            
            // Compute the total combinations of pairs that have 'i' as a common divisor
            long totalPairsWithDivisor = (multiplesCount * (multiplesCount - 1)) / 2;
            
            // Subtract pairs belonging to larger multiples to isolate pairs where GCD is exactly 'i'
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairsWithDivisor -= exactGcdPairs[j];
            }
            exactGcdPairs[i] = totalPairsWithDivisor;
        }

        // Build a prefix sum array to map running combination indices to their respective GCD values
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + exactGcdPairs[i];
        }

        // Answer each query by locating its position inside our prefix distribution timeline
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long targetIndex = queries[i];
            
            // Perform a binary search to discover the lowest GCD index that contains the target position
            int low = 1, high = maxVal, result = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSum[mid] > targetIndex) {
                    result = mid;
                    high = mid - 1; // Look left to see if a smaller GCD also covers this index
                } else {
                    low = mid + 1; // Look right because current window does not reach targetIndex yet
                }
            }
            answer[i] = result;
        }

        return answer;
    }
}