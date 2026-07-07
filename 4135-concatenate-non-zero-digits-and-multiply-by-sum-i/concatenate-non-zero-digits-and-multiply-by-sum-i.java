class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        long multiplier = 1;

        // WHAT: Process the number digit by digit mathematically from right to left
        // WHY: Eliminates string parsing conversion overhead, achieving O(1) space complexity
        while (n > 0) {
            int digit = n % 10;
            
            if (digit != 0) {
                // Prepend the digit to the left side of our growing number 'x'
                x += digit * multiplier;
                // Scale the multiplier to shift the next valid digit into the higher place-value position
                multiplier *= 10;
                // Accumulate the digit sum
                sum += digit;
            }
            
            n /= 10; // Move to the next digit
        }

        return x * sum;
    }
}