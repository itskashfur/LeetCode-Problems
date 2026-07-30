import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count frequency of each letter
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Sort frequencies in ascending order
        Arrays.sort(freq);

        int totalPushes = 0;

        // Step 3: Iterate backwards (from most frequent to least frequent)
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break; // Stop early if no more characters left

            // (25 - i) maps the most frequent character to index 0, second to 1, etc.
            int groupIdx = 25 - i;
            int pushCost = (groupIdx / 8) + 1;

            totalPushes += pushCost * freq[i];
        }

        return totalPushes;
    }
}