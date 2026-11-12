class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int best = 1, up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            up = nums[i - 1] < nums[i] ? up + 1 : 1;  // increasing streak ending at nums[i]
            down = nums[i - 1] > nums[i] ? down + 1 : 1;  // decreasing streak ending at nums[i]
            best = Math.max(best, Math.max(up, down));
        }
        return best;
    }
}