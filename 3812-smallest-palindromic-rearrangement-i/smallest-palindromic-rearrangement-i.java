class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int halfLen = n / 2;
        
        // Step 1: Extract the first half of the string
        char[] half = s.substring(0, halfLen).toCharArray();
        
        // Step 2: Sort the half lexicographically
        Arrays.sort(half);
        
        String left = new String(half);
        
        // Step 3: Get middle character if length is odd
        String mid = (n % 2 != 0) ? String.valueOf(s.charAt(halfLen)) : "";
        
        // Step 4: Reverse the sorted half to get the right mirror image
        String right = new StringBuilder(left).reverse().toString();
        
        // Step 5: Combine into the final smallest palindromic string
        return left + mid + right;
    }
}