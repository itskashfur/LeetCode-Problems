import java.util.Stack;

class Solution {
    public String smallestSubsequence(String s) {
        // Track the final index location for each character across the entire string
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // Use a boolean array to check if a character is already placed inside our active stack
        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            
            // If the character has already been included in our path, skip it to preserve uniqueness
            if (seen[curr - 'a']) {
                continue;
            }
            
            // Evict larger characters from the top if they appear again later in the string
            while (!stack.isEmpty() && stack.peek() > curr && lastIndex[stack.peek() - 'a'] > i) {
                seen[stack.pop() - 'a'] = false;
            }
            
            // Push the current character into our monotonic stack arrangement and mark it as collected
            stack.push(curr);
            seen[curr - 'a'] = true;
        }
        
        // Assemble the final result string from our cleanly arranged stack characters
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}