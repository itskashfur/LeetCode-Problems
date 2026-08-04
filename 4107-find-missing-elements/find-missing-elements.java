import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        Set<Integer> present = new HashSet<>();

        // Find min, max, and store elements in a set
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
            present.add(num);
        }

        List<Integer> result = new ArrayList<>();

        // Iterate through the full range [minVal, maxVal]
        for (int i = minVal; i <= maxVal; i++) {
            if (!present.contains(i)) {
                result.add(i);
            }
        }

        return result;
    }
}