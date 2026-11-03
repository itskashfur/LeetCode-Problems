class Solution {
    public int minMoves(int[] nums) {
        int max = 0;
        int sum = 0;
        for (int x : nums) {
            max = Math.max(max, x);
            sum += x;
        }
        return max * nums.length - sum;
    }
}