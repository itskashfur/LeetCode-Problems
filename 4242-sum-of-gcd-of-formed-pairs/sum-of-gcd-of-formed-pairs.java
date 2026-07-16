class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        
        int currentMax = 0;
        
        // Populate the prefixGcd array by matching each number with the highest value seen up to this index
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            prefixGcd[i] = computeGcd(nums[i], currentMax);
        }
        
        // Sort ascending so we can cleanly match elements from both extremes of the range
        Arrays.sort(prefixGcd);
        
        long totalSum = 0;
        int left = 0;
        int right = n - 1;
        
        // Use two pointers to pair the smallest remaining values with the largest remaining values
        while (left < right) {
            totalSum += computeGcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return totalSum;
    }
    
    // Standard iterative Euclidean algorithm to calculate the Greatest Common Divisor
    private int computeGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}