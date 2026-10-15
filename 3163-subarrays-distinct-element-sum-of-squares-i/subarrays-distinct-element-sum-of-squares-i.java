class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size(), total = 0;
        for (int i = 0; i < n; i++) {
            BitSet seen = new BitSet(101);  // values are 1..100
            for (int j = i; j < n; j++) {
                seen.set(nums.get(j));  // a repeat doesn't change the count
                int d = seen.cardinality();
                total += d * d;
            }
        }
        return total;
    }
}