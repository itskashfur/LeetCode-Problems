class Solution {
    public int maxProduct(int n) {
        int max1 = 0; // Tracks the largest digit
        int max2 = 0; // Tracks the second largest digit
        
        while (n > 0) {
            int digit = n % 10;
            n /= 10;
            
            if (digit >= max1) {
                max2 = max1;
                max1 = digit;
            } else if (digit > max2) {
                max2 = digit;
            }
        }
        
        return max1 * max2;
    }
}