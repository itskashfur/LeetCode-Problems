class Solution {
    public int maximumLength(int[] nums) {
        // WHAT: Create a frequency map to count occurrences of each number.
        // WHY: We need O(1) lookups to check if we have enough copies of 'x' and its squares to build the chain.
        Map<Long, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put((long) num, counts.getOrDefault((long) num, 0) + 1);
        }

        int maxLen = 1; // Any single element can always form a valid subset of length 1

        // WHAT: Handle the edge case for '1' separately.
        // WHY: 1 squared is always 1, so it creates an infinite loop if processed normally.
        if (counts.containsKey(1L)) {
            int oneCount = counts.get(1L);
            // An odd count can all be used, an even count needs 1 removed to keep the sequence length odd.
            maxLen = Math.max(maxLen, oneCount % 2 == 1 ? oneCount : oneCount - 1);
        }

        // WHAT: Iterate through each unique number > 1 to treat it as the base 'x' of our pattern.
        for (long x : counts.keySet()) {
            if (x == 1) continue;

            int currentLen = 0;
            long curr = x;

            // WHAT: Keep squaring the current number as long as it exists in our map.
            // WHY: To build the symmetric halves, we must have at least 2 copies of each non-peak element.
            while (counts.containsKey(curr)) {
                int count = counts.get(curr);
                
                if (count >= 2) {
                    currentLen += 2; // Allocate 1 copy to the left half and 1 to the right half
                    curr = curr * curr; // Move to the next square level
                } else {
                    // If we only have 1 copy, this element MUST be the peak of our chain.
                    currentLen += 1;
                    break;
                }
            }

            // WHAT: Correct the length if the loop ended because the next square was missing entirely.
            // WHY: If the loop terminated because counts.containsKey(curr) is false, the previous element 
            // was wrongfully credited with 2 copies when it should have been the peak (only needing 1 copy).
            if (!counts.containsKey(curr) && currentLen > 0) {
                currentLen -= 1;
            }

            maxLen = Math.max(maxLen, currentLen);
        }

        return maxLen;
    }
}