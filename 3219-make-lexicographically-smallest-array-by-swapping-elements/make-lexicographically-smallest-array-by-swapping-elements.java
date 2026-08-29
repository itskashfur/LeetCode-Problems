import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }

        // Sort by value
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            int j = i;
            List<Integer> indices = new ArrayList<>();
            
            // Find all elements in the same connected component
            while (j < n && (j == i || sorted[j][0] - sorted[j - 1][0] <= limit)) {
                indices.add(sorted[j][1]);
                j++;
            }

            // Sort original indices to place smallest values in leftmost positions
            Collections.sort(indices);

            // Assign sorted values of the component back to original indices
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = sorted[i + k][0];
            }

            i = j; // Move to next group
        }

        return result;
    }
}