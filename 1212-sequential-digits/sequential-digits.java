import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String sample = "123456789";
        List<Integer> ans = new ArrayList<>();
        
        // Loop through all possible lengths of sequential digits (from 2 up to 9)
        for (int length = 2; length <= 9; length++) {
            // Slide a window of 'length' size across the sample string
            for (int start = 0; start <= 9 - length; start++) {
                // Extract the substring fragment
                String sub = sample.substring(start, start + length);
                int num = Integer.parseInt(sub);
                
                // Add to our results list if it fits inside the range
                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }
        return ans;
    }
}