import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int smallest = Integer.MAX_VALUE;
        int largest = Integer.MIN_VALUE;

        for (int x : nums) {
            smallest = Math.min(smallest, x);
            largest = Math.max(largest, x);
        }

        boolean[] present = new boolean[largest - smallest + 1];
        for (int x : nums) {
            present[x - smallest] = true;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < present.length; i++) {
            if (!present[i]) {
                list.add(smallest + i);
            }
        }

        return list;
    }
}