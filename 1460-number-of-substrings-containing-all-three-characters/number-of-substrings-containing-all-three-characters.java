class Solution {
    public int numberOfSubstrings(String s) {
        int res = 0;
        
        // Array to track the last seen index of characters 'a', 'b', and 'c'
        // Initialized to -1 because we haven't encountered any characters yet
        int[] lastSeen = {-1, -1, -1};
        
        // Traverse through the string character by character
        for (int i = 0; i < s.length(); i++) {
            
            // Map character to index: 'a' -> 0, 'b' -> 1, 'c' -> 2
            // s.charAt(i) - 'a' converts the char ASCII value to a 0-based index
            lastSeen[s.charAt(i) - 'a'] = i;
            
            // The number of valid substrings ending at the current index 'i' is 
            // determined by the smallest index among the last seen positions of 'a', 'b', and 'c'.
            // Any substring starting from index 0 up to min(lastSeen) is valid.
            int minIdx = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            
            // Since indices are 0-based, there are exactly (minIdx + 1) valid starting positions
            res += minIdx + 1;
        }
        
        return res;
    }
}