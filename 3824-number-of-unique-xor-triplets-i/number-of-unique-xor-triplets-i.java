class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Handle small base cases directly
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // For n >= 3, the result is the smallest power of 2 strictly greater than n
        // Integer.highestOneBit(n) finds the largest power of 2 <= n
        int maxPowerOfTwo = Integer.highestOneBit(n);
        
        // Multiply by 2 to get 2^(MSB + 1)
        return maxPowerOfTwo * 2;
    }
}