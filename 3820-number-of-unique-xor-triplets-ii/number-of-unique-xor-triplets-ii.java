class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Find the maximum element to bound our max XOR array size
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }
        
        // Find the smallest power of 2 greater than maxVal (up to 2048)
        int mx = 1;
        while (mx <= maxVal) {
            mx <<= 1;
        }
        mx <<= 1; // Double for maximum XOR headroom

        // Step 1: Compute all possible pairwise XOR results (a ^ b)
        boolean[] validPairs = new boolean[mx];
        for (int a : nums) {
            for (int b : nums) {
                validPairs[a ^ b] = true;
            }
        }

        // Step 2: Combine valid pairs with a 3rd element c
        boolean[] validTriplets = new boolean[mx];
        for (int ab = 0; ab < mx; ab++) {
            if (validPairs[ab]) {
                for (int c : nums) {
                    validTriplets[ab ^ c] = true;
                }
            }
        }

        // Step 3: Count total unique triplet XOR values
        int count = 0;
        for (boolean present : validTriplets) {
            if (present) {
                count++;
            }
        }

        return count;
    }
}