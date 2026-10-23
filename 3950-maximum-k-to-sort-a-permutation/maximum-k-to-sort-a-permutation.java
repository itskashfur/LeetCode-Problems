class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int res = -1;
        for(int i = 0 ; i < nums.length ; ++i){
            if(nums[i] != i){
                if(res == -1)res = i;
                else res = res & i;
            }
        }
        return res == -1 ? 0 : res;
    }
}