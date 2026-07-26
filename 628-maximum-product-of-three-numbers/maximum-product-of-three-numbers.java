class Solution {
    public int maximumProduct(int[] nums) {
        // Step 1: Initialize boundaries
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        
        // Step 2: Single linear scan to find 3 max and 2 min elements
        for (int n : nums) {
            // Update top 3 largest
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
            
            // Update top 2 smallest
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        
        // Step 3 & 4: Return maximum between the two possible maximum candidates
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}

// Time Complexity: O(N)
// Space Complexity: O(1)