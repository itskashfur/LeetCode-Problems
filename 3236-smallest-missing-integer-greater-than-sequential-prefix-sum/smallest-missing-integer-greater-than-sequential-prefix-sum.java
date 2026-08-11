import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        // 1. Calculate the sum of the longest sequential prefix starting at index 0
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break; // Stop at the first element that breaks the sequence
            }
        }

        // 2. Put all elements in a set for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // 3. Find the smallest missing integer >= sum
        int ans = sum;
        while (numSet.contains(ans)) {
            ans++;
        }

        return ans;
    }
}