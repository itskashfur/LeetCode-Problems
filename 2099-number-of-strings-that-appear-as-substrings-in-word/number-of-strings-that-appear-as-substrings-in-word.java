class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        
        // Loop through each individual pattern string in the array
        for (String pattern : patterns) {
            // Check if the 'word' contains the current pattern as a substring
            if (word.contains(pattern)) {
                count++; // Increment the counter if a match is found
            }
        }
        
        // Return the total number of matching patterns
        return count;
    }
}