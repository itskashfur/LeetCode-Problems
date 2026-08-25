import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Store all elements of nums in a HashSet for O(1) lookup time
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Iterate through positive multiples of k (k, 2k, 3k, ...)
        int multiple = k;
        while (set.contains(multiple)) {
            multiple += k;
        }

        // Return the first multiple not present in nums
        return multiple;
    }
}