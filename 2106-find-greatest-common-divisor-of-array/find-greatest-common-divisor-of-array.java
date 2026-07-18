class Solution {
    public int findGCD(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        
        // Single linear scan to find the smallest and largest numbers in the array
        for (int num : nums) {
            if (num < minVal) {
                minVal = num;
            }
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        // Apply the Euclidean algorithm on the two extremes to find their GCD
        return computeGcd(minVal, maxVal);
    }
    
    // Standard iterative Euclidean method to find the Greatest Common Divisor
    private int computeGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}