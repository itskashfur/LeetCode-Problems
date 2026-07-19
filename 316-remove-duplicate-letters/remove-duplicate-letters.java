import java.util.Stack;

class Solution {
    public String removeDuplicateLetters(String s) {
        // Record the last index position where each letter appears in the string
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // Track whether a character is currently present inside our result stack
        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            
            // If the character is already in the stack, skip it to maintain uniqueness
            if (seen[curr - 'a']) {
                continue;
            }
            
            // Pop elements from the stack if they are larger than the current character 
            // AND they show up again later in the string
            while (!stack.isEmpty() && stack.peek() > curr && lastIndex[stack.peek() - 'a'] > i) {
                seen[stack.pop() - 'a'] = false;
            }
            
            // Insert the current character into the stack and mark it as active
            stack.push(curr);
            seen[curr - 'a'] = true;
        }
        
        // Rebuild the final string from the characters collected in the stack
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}