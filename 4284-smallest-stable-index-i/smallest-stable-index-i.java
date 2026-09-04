class Solution {
    static int[] suf = new int[100];
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        suf[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--)
            suf[i] = Math.min(suf[i + 1], nums[i]);

        int maxSoFar = 0;
        for (int i = 0; i < n; i++) {
            maxSoFar = Math.max(maxSoFar, nums[i]);
            if (maxSoFar <= k + suf[i])
                return i;
        }

        return -1;
    }
}