import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Case 1: k == 1
        if (k == 1) {
            int maxVal = -1;
            for (int num : nums) {
                if (freq.get(num) == 1) {
                    maxVal = Math.max(maxVal, num);
                }
            }
            return maxVal;
        }

        // Case 2: k == n
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Case 3: 1 < k < n
        int ans = -1;
        // Check first element
        if (freq.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }
        // Check last element
        if (freq.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}