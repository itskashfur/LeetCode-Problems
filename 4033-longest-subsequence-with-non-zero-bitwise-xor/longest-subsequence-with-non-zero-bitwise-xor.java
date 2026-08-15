class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }

        // Case 1: All elements are 0
        if (!hasNonZero) {
            return 0;
        }

        // Case 2: XOR of all elements is already non-zero
        if (totalXor != 0) {
            return nums.length;
        }

        // Case 3: Total XOR is 0, but non-zero elements exist
        return nums.length - 1;
    }
}