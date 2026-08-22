class Solution {
    public boolean checkDivisibility(int n) {
        int digitSum = 0;
        int digitProduct = 1;
        int temp = n;

        // Extract each digit
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProduct *= digit;
            temp /= 10;
        }

        int total = digitSum + digitProduct;

        // Check if n is divisible by the combined sum and product
        return n % total == 0;
    }
}