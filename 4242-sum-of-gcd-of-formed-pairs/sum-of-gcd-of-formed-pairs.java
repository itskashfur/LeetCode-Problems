import java.util.Arrays;

class Solution {
   
    public long gcdSum(int[] arr) {
        // Track the array length to size our intermediate storage array
        int n = arr.length;
        int[] prefi = new int[n];
        int mx = 0;
        
        // Step 1: Compute the prefix GCD values by pairing the current element with the historical maximum
        for (int i = 0; i < n; i++) {
            if (arr[i] > mx) {
                mx = arr[i];
            }
            prefi[i] = gcd(mx, arr[i]);
        }
        
        // Step 2: Sort the array ascending to enable systematic symmetric pairing from both boundaries
        Arrays.sort(prefi);
        
        int i = 0;
        int j = n - 1;
        long sum = 0;
        
        // Step 3: Use two pointers to pair the smallest available terms with the largest available terms
        while (i < j) {
            sum += gcd(prefi[i], prefi[j]);
            i++;
            j--;
        }
        return sum;
    }
    // Standard iterative Euclidean algorithm to calculate the Greatest Common Divisor
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}