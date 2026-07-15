class Solution {
    public int gcdOfOddEvenSums(int n) {
        // MATHEMATICAL PROOF WHY THIS WORKS:
        // 1. The sum of the first 'n' odd numbers is always equal to n * n.
        //    Example: for n = 4 -> 1 + 3 + 5 + 7 = 16 (which is 4 * 4)
        //
        // 2. The sum of the first 'n' even numbers is always equal to n * (n + 1).
        //    Example: for n = 4 -> 2 + 4 + 6 + 8 = 20 (which is 4 * 5)
        //
        // 3. We need to find GCD(n * n, n * (n + 1)).
        //    We can factor out 'n' from both sides:
        //    GCD = n * GCD(n, n + 1)
        //
        // 4. Consecutive integers (like n and n + 1) are always coprime, meaning GCD(n, n + 1) is always 1.
        //    Therefore, the calculation simplifies to: n * 1 = n.
        
        return n; 
    }
}